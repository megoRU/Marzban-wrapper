package org.marzban.api.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.marzban.impl.APIRequestData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest implements APIRequestData {

    @Builder.Default
    private String status = "active";

    private String username;

    @Builder.Default
    private String note = "";

    private UserCommon.Proxies proxies;
    private UserCommon.Inbounds inbounds;

    @Builder.Default
    @JsonProperty("data_limit")
    @SerializedName("data_limit")
    private Long dataLimit = 0L;

    @Builder.Default
    private Long expire = 0L;

    @Builder.Default
    @JsonProperty("on_hold_expire_duration")
    @SerializedName("on_hold_expire_duration")
    private Integer onHoldExpireDuration = 0;

    @JsonProperty("on_hold_timeout")
    @SerializedName("on_hold_timeout")
    private String onHoldTimeout;

    @Builder.Default
    @JsonProperty("data_limit_reset_strategy")
    @SerializedName("data_limit_reset_strategy")
    private String dataLimitResetStrategy = "no_reset";

    public UserRequest(String username, UserCommon.Proxies proxies, UserCommon.Inbounds inbounds) {
        this.username = username;
        this.proxies = proxies;
        this.inbounds = inbounds;
    }

    @Getter
    public enum Protocol {

        VLESS_TCP_REALITY("VLESS TCP REALITY"),
        VLESS_TCP_TLS("VLESS TCP TLS"),
        SHADOWSOCKS_TCP("Shadowsocks TCP"),
        TROJAN_TCP_XTLS("TROJAN TCP XTLS"),
        TROJAN_TCP_TLS("TROJAN TCP TLS"),
        VMESS_TCP("VMESS TCP"),
        VMESS_TCP_TLS("VMESS TCP TLS"),
        NONE("");

        private final String value;

        Protocol(String value) {
            this.value = value;
        }

        public static Protocol find(String value) {
            if (value == null || value.isEmpty()) return Protocol.NONE;
            for (Protocol version : values()) {
                if (version.value.equals(value)) return version;
            }
            return null;
        }
    }
}