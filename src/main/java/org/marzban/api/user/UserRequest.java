package org.marzban.api.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.marzban.impl.APIRequestData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequest implements APIRequestData {

    @Builder.Default
    private String status = "active";

    private String username;

    @Builder.Default
    private String note = "";

    private Proxies proxies;

    @Builder.Default
    @JsonProperty("data_limit")
    private Integer dataLimit = 0;

    @Builder.Default
    private Integer expire = 0;

    @Builder.Default
    @JsonProperty("on_hold_expire_duration")
    private Integer onHoldExpireDuration = 0;

    @JsonProperty("on_hold_timeout")
    private String onHoldTimeout;

    @Builder.Default
    @JsonProperty("data_limit_reset_strategy")
    private String dataLimitResetStrategy = "no_reset";

    private Inbounds inbounds;

    public UserRequest(String username, Proxies proxies, Inbounds inbounds) {
        this.username = username;
        this.proxies = proxies;
        this.inbounds = inbounds;
    }

    @Getter
    @Setter
    @Builder
    public static class Proxies {
        private Vless vless;
        private Shadowsocks shadowsocks;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Shadowsocks {
        private String password;
        private String method;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Vless {
        private String id;

        @Builder.Default
        private String flow = "xtls-rprx-vision";
    }

    @Getter
    @Setter
    @Builder
    public static class Inbounds {
        private List<String> vless;
        private List<String> shadowsocks;
        private List<String> vmess;
        private List<String> trojan;
    }

    @Getter
    public enum Protocol {

        VLESS_TCP_REALITY("VLESS TCP REALITY"),
        VLESS_TCP_TLS("VLESS TCP TLS"),
        SHADOWSOCKS_TCP("SHADOWSOCKS TCP"),
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
                if (version.value.equals(value)) {
                    return version;
                }
            }
            return null;
        }
    }
}