package org.accenture.mappers.role;

import org.accenture.domain.dto.RoleDto;
import org.accenture.domain.models.Role;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 17:21
 * @project: FIGHTSTUFF
 */

@Service
public class DtoToRole implements Function<RoleDto, Role> {
    @Override
    public Role apply(RoleDto roleDto) {
        return new Role(
                roleDto.getId(),
                roleDto.getLabel(),
                roleDto.getPermissions()
        );
    }
}
