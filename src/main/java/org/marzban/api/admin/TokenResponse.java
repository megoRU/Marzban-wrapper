package org.marzban.api.admin;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.marzban.impl.APIObject;
import org.marzban.impl.APIRequestData;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse implements APIObject, APIRequestData {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;
}