package com.example.biedronka_saver.model.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    USER,
    ADMIN;

    public SimpleGrantedAuthority asAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + this.name());
    }

    @Override
    public String toString() {
        return name();
    }
}
