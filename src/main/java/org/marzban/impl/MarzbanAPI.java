package org.marzban.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.marzban.api.admin.TokenRequest;
import org.marzban.api.admin.TokenResponse;
import org.marzban.api.user.UserSearchRequest;
import org.marzban.api.response.DeleteUserResponse;
import org.marzban.api.response.UserResponse;
import org.marzban.exceptions.UnsuccessfulHttpException;
import org.marzban.api.user.*;

import java.io.IOException;
import java.util.Objects;

public interface MarzbanAPI {

    /**
     * @param userRequest new user
     * @return {@link UserResponse} userResponse
     */
    UserResponse addUser(@NotNull UserRequest userRequest) throws IOException, UnsuccessfulHttpException;

    /**
     * @param userName user name
     * @return {@link UserResponse} userResponse
     */
    UserResponse getUser(@NotNull String userName) throws IOException, UnsuccessfulHttpException;

    /**
     * @param userSearchRequest data for search
     * @return {@link UsersResponse} users
     */
    UsersResponse userSearch(@NotNull UserSearchRequest userSearchRequest) throws IOException, UnsuccessfulHttpException;

    /**
     * @param userName user name
     * @return {@link DeleteUserResponse} deleteUserResponse
     */
    DeleteUserResponse deleteUser(@NotNull String userName) throws IOException, UnsuccessfulHttpException;

    /**
     * @param tokenRequest {@link TokenRequest}
     * @return {@link DeleteUserResponse} deleteUserResponse
     */
    TokenResponse setSession(TokenRequest tokenRequest) throws IOException, UnsuccessfulHttpException;

    class Builder {

        // Required
        private TokenRequest tokenRequest;
        private boolean devMode;
        private String host;

        /**
         * This enables LOGS
         */
        public Builder enableDevMode() {
            this.devMode = true;
            return this;
        }

        /**
         * tokenRequest
         */
        @Contract("null -> fail")
        public Builder setTokenRequest(TokenRequest tokenRequest) {
            Objects.requireNonNull(tokenRequest);
            this.tokenRequest = tokenRequest;
            return this;
        }

        /**
         * Example: <a href="https://example.com:8000">https://example.com:8000</a>
         * <br>
         * Only in this format. Without last slash
         */
        @Contract("null -> fail")
        public Builder setHost(String host) {
            Objects.requireNonNull(host);
            this.host = host;
            return this;
        }

        /**
         * @throws IllegalArgumentException if some fields null
         */
        public MarzbanAPI build() throws IOException, UnsuccessfulHttpException {
            if (tokenRequest.getUsername() == null)
                throw new IllegalArgumentException("login cannot be null!");
            else if (tokenRequest.getPassword() == null)
                throw new IllegalArgumentException("password cannot be null!");
            else if (host == null)
                throw new IllegalArgumentException("host cannot be null!");
            else
                return new MarzbanAPIImpl(tokenRequest, devMode, host);
        }
    }
}