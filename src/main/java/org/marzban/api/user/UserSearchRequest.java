package org.marzban.api.user;

import lombok.*;
import org.marzban.impl.APIRequestData;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserSearchRequest implements APIRequestData {

    private Integer offset;
    private Integer limit = 10;
    private List<String> username;
    private String search;
    private List<String> admin;
    private Status status;
    private String sort = "-created_at";

    @Getter
    public enum Status {
        ACTIVE("active"),
        DISABLED("disabled"),
        LIMITED("limited"),
        EXPIRED("expired"),
        ON_HOLD("on_hold"),
        NONE("");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public static Status fromValue(String value) {
            for (Status status : Status.values()) {
                if (status.value.equalsIgnoreCase(value)) {
                    return status;
                }
            }
            return NONE;
        }
    }
}

