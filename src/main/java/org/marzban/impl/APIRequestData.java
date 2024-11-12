package org.marzban.impl;

import org.marzban.utils.JsonUtil;

public interface APIRequestData {

    default String toJson() {
        return JsonUtil.toJson(this);
    }
}