package org.acmapis.commercial_management_system.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER"),
    MANAGER("MODERATOR");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
}
