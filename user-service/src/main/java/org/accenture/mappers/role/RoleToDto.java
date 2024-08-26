package org.accenture.mappers.role;

import org.accenture.domain.dto.RoleDto;
import org.accenture.domain.models.Role;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 16:41
 * @project: FIGHTSTUFF
 */

@Service
public class RoleToDto implements Function<Role, RoleDto> {
    @Override
    public RoleDto apply(Role role) {
        return new RoleDto(
                role.getId(),
                role.getLabel(),
                role.getPermissions()
        );
    }
}
