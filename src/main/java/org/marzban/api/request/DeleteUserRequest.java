package org.marzban.api.request;

public class DeleteUserRequest extends APIRequest {

    public DeleteUserRequest(String host, String userName) {
        super(String.format("%s/api/user/%s", host, userName), RequestMethod.DELETE);
    }
}
