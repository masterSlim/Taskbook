package tbspring.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_EXECUTOR,
    ROLE_MANAGER,
    ROLE_USER,
    ROLE_ADMIN
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
