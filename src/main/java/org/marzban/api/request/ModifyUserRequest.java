package org.marzban.api.request;

import org.marzban.api.user.UserRequest;

public class ModifyUserRequest extends APIRequest {

    public ModifyUserRequest(String host, UserRequest userRequest) {
        super(String.format("%s/api/user/%s", host, userRequest.getUsername()), RequestMethod.PUT, userRequest);
    }
}
