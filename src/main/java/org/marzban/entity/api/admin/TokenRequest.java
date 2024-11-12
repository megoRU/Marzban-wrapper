package org.marzban.entity.api.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.marzban.impl.APIObject;
import org.marzban.impl.APIRequestData;

@Getter
@Setter
@AllArgsConstructor
public class TokenRequest implements APIObject, APIRequestData {

    private String grantType;
    private final String username;
    private final String password;
    private String scope;
    private String clientId;
    private String clientSecret;

    public TokenRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}