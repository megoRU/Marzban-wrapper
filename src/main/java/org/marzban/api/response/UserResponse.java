package org.marzban.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.marzban.api.user.UserCommon;
import org.marzban.impl.APIObject;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements APIObject {

    private UserCommon.Proxies proxies;
    private String expire;

    @SerializedName("data_limit")
    @JsonProperty("data_limit")
    private Long dataLimit;

    @SerializedName("data_limit_reset_strategy")
    @JsonProperty("data_limit_reset_strategy")
    private String dataLimitResetStrategy;

    private UserCommon.Inbounds inbounds;
    private String note;

    @SerializedName("sub_updated_at")
    @JsonProperty("sub_updated_at")
    private String subUpdatedAt;

    @SerializedName("sub_last_user_agent")
    @JsonProperty("sub_last_user_agent")
    private String subLastUserAgent;

    @SerializedName("online_at")
    @JsonProperty("online_at")
    private String onlineAt;

    @SerializedName("on_hold_expire_duration")
    @JsonProperty("on_hold_expire_duration")
    private String onHoldExpireDuration;

    @SerializedName("on_hold_timeout")
    @JsonProperty("on_hold_timeout")
    private String onHoldTimeout;

    @SerializedName("auto_delete_in_days")
    @JsonProperty("auto_delete_in_days")
    private String autoDeleteInDays;

    private String username;
    private String status;

    @SerializedName("used_traffic")
    @JsonProperty("used_traffic")
    private long usedTraffic;

    @SerializedName("lifetime_used_traffic")
    @JsonProperty("lifetime_used_traffic")
    private long lifetimeUsedTraffic;

    @SerializedName("created_at")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private List<String> links;

    @SerializedName("subscription_url")
    @JsonProperty("subscription_url")
    private String subscriptionUrl;

    @SerializedName("excluded_inbounds")
    @JsonProperty("excluded_inbounds")
    private ExcludedInbounds excludedInbounds;

    private Admin admin;

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
        @JsonProperty("is_sudo")
        private boolean isSudo;

        @SerializedName("telegram_id")
        @JsonProperty("telegram_id")
        private Object telegramId;

        @SerializedName("discord_webhook")
        @JsonProperty("discord_webhook")
        private Object discordWebhook;
    }
}