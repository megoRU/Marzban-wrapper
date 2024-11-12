package org.marzban.entity.api.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marzban.impl.APIObject;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteUserResponse implements APIObject {

    private String detail;

    public boolean isSuccess() {
        if (detail != null) {
            return detail.equals("User successfully deleted");
        } else return false;
    }
}
