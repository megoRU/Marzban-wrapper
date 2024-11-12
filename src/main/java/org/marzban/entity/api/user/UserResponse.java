package org.marzban.entity.api.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.marzban.impl.APIObject;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserResponse implements APIObject {

    private Proxies proxies;
    private String expire;

    @SerializedName("data_limit")
    private String dataLimit;

    @SerializedName("data_limit_reset_strategy")
    private String dataLimitResetStrategy;

    private Inbounds inbounds;
    private String note;

    @SerializedName("sub_updated_at")
    private String subUpdatedAt;

    @SerializedName("sub_last_user_agent")
    private String subLastUserAgent;

    @SerializedName("online_at")
    private String onlineAt;

    @SerializedName("on_hold_expire_duration")
    private String onHoldExpireDuration;

    @SerializedName("on_hold_timeout")
    private String onHoldTimeout;

    @SerializedName("auto_delete_in_days")
    private String autoDeleteInDays;

    private String username;
    private String status;

    @SerializedName("used_traffic")
    private long usedTraffic;

    @SerializedName("lifetime_used_traffic")
    private long lifetimeUsedTraffic;

    @SerializedName("created_at")
    private LocalDateTime createdAt;

    private List<String> links;

    @SerializedName("subscription_url")
    private String subscriptionUrl;

    @SerializedName("excluded_inbounds")
    private ExcludedInbounds excludedInbounds;

    private Admin admin;

    @Data
    @NoArgsConstructor
    public static class Proxies {
        private Vless vless;
    }

    @Data
    @NoArgsConstructor
    public static class Vless {
        private String id;
        private String flow;
    }

    @Data
    @NoArgsConstructor
    public static class Inbounds {
        private List<String> vless;
    }

    @Data
    @NoArgsConstructor
    public static class ExcludedInbounds {
        private List<String> vless;
    }

    @Data
    @NoArgsConstructor
    public static class Admin {
        private String username;

        @SerializedName("is_sudo")
        private boolean isSudo;

        @SerializedName("telegram_id")
        private Object telegramId;

        @SerializedName("discord_webhook")
        private Object discordWebhook;
    }
}