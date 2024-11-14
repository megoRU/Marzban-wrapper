package org.marzban.api.request;

import org.marzban.api.user.UserRequest;

public class AddUserRequest extends APIRequest {

    public AddUserRequest(String host, UserRequest userRequest) {
        super(String.format("%s/api/user", host), RequestMethod.POST, userRequest);
    }
}
