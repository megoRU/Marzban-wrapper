package org.marzban.api.user;

import lombok.*;
import java.util.List;

public final class UserCommon {

    private UserCommon() {}

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Proxies {
        private Vless vless;
        private Shadowsocks shadowsocks;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Vless {
        private String id;

        @Builder.Default
        private String flow = "xtls-rprx-vision";
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Shadowsocks {
        private String password;
        private String method;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Inbounds {
        private List<String> vless;
        private List<String> shadowsocks;
        private List<String> vmess;
        private List<String> trojan;
    }
}