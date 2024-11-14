package org.marzban.api.request;

public class GetUserRequest extends APIRequest {

    public GetUserRequest(String host, String userName) {
        super(String.format("%s/api/user/%s", host, userName), RequestMethod.GET);
    }
}
