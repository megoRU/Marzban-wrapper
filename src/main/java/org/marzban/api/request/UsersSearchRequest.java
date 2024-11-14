package org.marzban.api.request;

import org.marzban.api.user.UserSearchRequest;

public class UsersSearchRequest extends APIRequest {

    public UsersSearchRequest(String host, UserSearchRequest userSearchRequest) {
        super(buildUrlWithParams(host, userSearchRequest), RequestMethod.GET, userSearchRequest);
    }

    private static String buildUrlWithParams(String host, UserSearchRequest userSearchRequest) {
        StringBuilder url = new StringBuilder(String.format("%s/api/users?", host));

        if (userSearchRequest.getOffset() != null) {
            url.append("offset=").append(userSearchRequest.getOffset()).append("&");
        }
        if (userSearchRequest.getLimit() != null) {
            url.append("limit=").append(userSearchRequest.getLimit()).append("&");
        }
        if (userSearchRequest.getUsername() != null && !userSearchRequest.getUsername().isEmpty()) {
            url.append("username=").append(String.join(",", userSearchRequest.getUsername())).append("&");
        }
        if (userSearchRequest.getSearch() != null) {
            url.append("search=").append(userSearchRequest.getSearch()).append("&");
        }
        if (userSearchRequest.getAdmin() != null && !userSearchRequest.getAdmin().isEmpty()) {
            url.append("admin=").append(String.join(",", userSearchRequest.getAdmin())).append("&");
        }
        if (userSearchRequest.getStatus() != null) {
            url.append("status=").append(userSearchRequest.getStatus().name()).append("&");
        }
        if (userSearchRequest.getSort() != null) {
            url.append("sort=").append(userSearchRequest.getSort()).append("&");
        }

        if (url.charAt(url.length() - 1) == '&') {
            url.deleteCharAt(url.length() - 1);
        }
        return url.toString();
    }
}