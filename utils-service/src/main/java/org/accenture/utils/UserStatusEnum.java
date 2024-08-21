package org.accenture.utils;

import lombok.Getter;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 16:00
 * @project: FIGHTSTUFF
 */

@Getter
public enum UserStatusEnum {
    ACCOUNT_VERIFIED("account_verified"),
    PENDING("pending"),
    ACCOUNT_NOT_VERIFIED("account_not_verified");

    private String status;

    UserStatusEnum(String status){
        this.status = status;
    }
}
