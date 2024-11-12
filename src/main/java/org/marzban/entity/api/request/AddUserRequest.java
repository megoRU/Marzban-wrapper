package org.marzban.entity.api.request;

import org.marzban.entity.api.user.UserRequest;

public class AddUserRequest extends APIRequest {

    public AddUserRequest(String host, UserRequest userRequest) {
        super(String.format("%s/api/user", host), RequestMethod.POST, userRequest);
    }
}
