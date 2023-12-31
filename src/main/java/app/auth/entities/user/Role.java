package app.auth.entities.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static app.auth.entities.user.Permission.ADMIN_CREATE;
import static app.auth.entities.user.Permission.ADMIN_DELETE;
import static app.auth.entities.user.Permission.ADMIN_READ;
import static app.auth.entities.user.Permission.ADMIN_UPDATE;
import static app.auth.entities.user.Permission.USER_CREATE_RECORD;
import static app.auth.entities.user.Permission.USER_DELETE_RECORD;
import static app.auth.entities.user.Permission.USER_READ_RECORDS;
import static app.auth.entities.user.Permission.USER_UPDATE;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    USER_READ_RECORDS,
                    USER_UPDATE,
                    USER_CREATE_RECORD,
                    USER_DELETE_RECORD
            )
    ),
    USER(
            Set.of(
                    USER_READ_RECORDS,
                    USER_UPDATE,
                    USER_CREATE_RECORD,
                    USER_DELETE_RECORD
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
