package org.marzban.impl;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.marzban.api.request.*;
import org.marzban.api.response.DeleteUserResponse;
import org.marzban.api.response.UserResponse;
import org.marzban.api.user.*;
import org.marzban.api.admin.TokenRequest;
import org.marzban.api.admin.TokenResponse;
import org.marzban.exceptions.UnsuccessfulHttpException;
import org.marzban.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class MarzbanAPIImpl implements MarzbanAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarzbanAPIImpl.class);
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private final String host;
    private String token;
    private final boolean devMode;

    protected MarzbanAPIImpl(TokenRequest tokenRequest, boolean devMode, String host) throws IOException, UnsuccessfulHttpException {
        this.host = host;
        this.devMode = devMode;
        //Устанавливаем сессию
        setSession(tokenRequest);
    }

    private void logResponse(String body) {
        if (devMode) System.out.printf("Body: %s%n", body);
    }

    @Override
    public UserResponse addUser(@NotNull UserRequest userRequest) throws IOException, UnsuccessfulHttpException {
        return parseResponse(UserResponse.class, new AddUserRequest(host, userRequest));
    }

    @Override
    public UserResponse getUser(@NotNull String userName) throws IOException, UnsuccessfulHttpException {
        return parseResponse(UserResponse.class, new GetUserRequest(host, userName));
    }

    @Override
    public UsersResponse userSearch(@NotNull UserSearchRequest userSearchRequest) throws IOException, UnsuccessfulHttpException {
        return parseResponse(UsersResponse.class, new UsersSearchRequest(host, userSearchRequest));
    }

    @Override
    public DeleteUserResponse deleteUser(@NotNull String userName) throws IOException, UnsuccessfulHttpException {
        return parseResponse(DeleteUserResponse.class, new DeleteUserRequest(host, userName));
    }

    @Override
    public TokenResponse setSession(TokenRequest tokenRequest) throws IOException, UnsuccessfulHttpException {
        return parseResponseToken(tokenRequest);
    }

    private TokenResponse parseResponseToken(@NotNull TokenRequest tokenRequest) throws IOException, UnsuccessfulHttpException {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        if (tokenRequest.getGrantType() != null) {
            formBodyBuilder.add("grant_type", tokenRequest.getGrantType());
        }
        if (tokenRequest.getUsername() != null) {
            formBodyBuilder.add("username", tokenRequest.getUsername());
        }
        if (tokenRequest.getPassword() != null) {
            formBodyBuilder.add("password", tokenRequest.getPassword());
        }
        if (tokenRequest.getScope() != null) {
            formBodyBuilder.add("scope", tokenRequest.getScope());
        }
        if (tokenRequest.getClientId() != null) {
            formBodyBuilder.add("client_id", tokenRequest.getClientId());
        }
        if (tokenRequest.getClientSecret() != null) {
            formBodyBuilder.add("client_secret", tokenRequest.getClientSecret());
        }

        RequestBody formBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url(String.format("%s/%s", host, "api/admin/token"))
                .post(formBody)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new UnsuccessfulHttpException(response.code(), response.message());
            } else {
                String body = response.body().string();
                if (devMode) logResponse(body);
                TokenResponse tokenResponse = JsonUtil.fromJson(body, TokenResponse.class);
                token = tokenResponse.getAccessToken();
                return tokenResponse;
            }
        }
    }

    private <T extends APIObject> T parseResponse(Class<T> tClass, @NotNull APIRequest apiRequest) throws IOException, UnsuccessfulHttpException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(apiRequest.getUrl())
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + token);

        if (apiRequest.getRequestMethod() == APIRequest.RequestMethod.GET) {
            requestBuilder = requestBuilder.get();
        } else if (apiRequest.getRequestMethod() == APIRequest.RequestMethod.DELETE) {
            requestBuilder = requestBuilder.delete();
        } else if (apiRequest.getRequestMethod() == APIRequest.RequestMethod.POST) {
            if (apiRequest.getData() != null) {
                requestBuilder.post(RequestBody.create(apiRequest.getData().toJson(), MEDIA_TYPE_JSON));
            } else {
                requestBuilder.post(RequestBody.create("{}", MEDIA_TYPE_JSON));
            }
        }

        Request request = requestBuilder.build();

        HttpUrl url = request.url();
        System.out.println(url.toString());

        try (Response response = CLIENT.newCall(request).execute()) {
            String responseBody = Objects.requireNonNull(response.body()).string();
            if (response.isSuccessful()) {
                logResponse(responseBody);
                return JsonUtil.fromJson(responseBody, tClass);
            } else {
                logResponse(responseBody);
                throw new UnsuccessfulHttpException(response.code(), response.message());
            }
        }
    }
}