package com.corecrew.tablemanager.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    ROLE_CUSTOMER,
    ROLE_MANAGER,
    ROLE_USER,
    ROLE_STAFF,
    ROLE_ADMIN;

    @JsonCreator
    public static UserRole fromString(String value) {
        for (UserRole role : UserRole.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + value);
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
