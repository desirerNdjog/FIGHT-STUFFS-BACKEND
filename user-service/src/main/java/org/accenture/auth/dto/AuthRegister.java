package org.accenture.auth.dto;

import lombok.*;
import org.accenture.domain.models.Role;
import org.accenture.utils.UserStatusEnum;

import java.util.Set;

/**
 * @author: desirejuniorndjog.
 * @created: 27/08/2024 : 18:50
 * @project: FLIGHTSTUFF
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegister {
    private String username;
    private String email;
    private String password;
    private UserStatusEnum userstatus;
    private Set<Role> roles;
}
