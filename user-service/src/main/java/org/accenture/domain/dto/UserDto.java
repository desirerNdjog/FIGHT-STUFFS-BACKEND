package org.accenture.domain.dto;

import lombok.*;
import org.accenture.domain.models.Role;
import org.accenture.utils.UserStatusEnum;

import java.util.Set;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 17:33
 * @project: FIGHTSTUFF
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean firstLogin;
    private UserStatusEnum userstatus;
    private Set<Role> roles;
}
