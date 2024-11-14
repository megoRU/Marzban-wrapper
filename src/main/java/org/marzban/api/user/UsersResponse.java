package org.marzban.api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.marzban.api.response.UserResponse;
import org.marzban.impl.APIObject;

import java.util.List;

@AllArgsConstructor
@Data
public class UsersResponse implements APIObject {

    private int total;
    private List<UserResponse> users;
}
