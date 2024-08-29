package org.accenture.mappers.permission;

import org.accenture.domain.dto.PermissionDto;
import org.accenture.domain.models.Permission;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 18:09
 * @project: FLIGHTSTUFF
 */

@Service
public class PermissionToDto implements Function<Permission, PermissionDto> {
    @Override
    public PermissionDto apply(Permission permission) {
        return new PermissionDto(
                permission.getId(),
                permission.getLabel(),
                permission.getCreated()
        );
    }
}
