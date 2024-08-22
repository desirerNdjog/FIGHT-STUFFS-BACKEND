package org.accenture.utils;

import lombok.Getter;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 16:34
 * @project: FIGHTSTUFF
 */

@Getter
public enum PermissionEnum {
    READ("read"),
    WRITE("write"),
    UPDATE("update"),
    DELETE("delete");

    private String permission;

    PermissionEnum(String permission){
        this.permission = permission;
    }
}
