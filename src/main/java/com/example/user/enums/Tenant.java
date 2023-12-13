package com.example.user.enums;

public enum Tenant {
    ADMIN("admin"),
    CC_USER("cc_user"),
    LOAN_USER("loan_user");

    private String tenant;

    Tenant(final String tenant) {
        this.tenant = tenant;
    }
}
