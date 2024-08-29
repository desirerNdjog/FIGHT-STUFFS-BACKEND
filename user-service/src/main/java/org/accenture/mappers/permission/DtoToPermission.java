package org.accenture.mappers.permission;

import org.accenture.domain.dto.PermissionDto;
import org.accenture.domain.models.Permission;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 18:10
 * @project: FLIGHTSTUFF
 */

@Service
public class DtoToPermission implements Function<PermissionDto, Permission> {
    @Override
    public Permission apply(PermissionDto permission) {
        return new Permission(
                permission.getId(),
                permission.getLabel(),
                permission.getCreated()
        );
    }
}
