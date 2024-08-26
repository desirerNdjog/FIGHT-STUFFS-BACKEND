package org.accenture.mappers.role;

import org.accenture.domain.models.Role;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 16:41
 * @project: FIGHTSTUFF
 */

@Service
public class RoleToDto implements Function<Role, RoleToDto> {
    @Override
    public RoleToDto apply(Role role) {
        return null;
    }
}
