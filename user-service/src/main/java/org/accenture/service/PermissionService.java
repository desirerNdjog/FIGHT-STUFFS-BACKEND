package org.accenture.service;

import org.accenture.domain.dto.PermissionDto;
import org.springframework.data.domain.Page;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 18:15
 * @project: FLIGHTSTUFF
 */

public interface PermissionService {
    Page<PermissionDto> listPermissionPaginate(String search, int page, int size);
    void create(PermissionDto permissionDto);
    PermissionDto update(PermissionDto permissionDto);
    PermissionDto findById(Long id);
    PermissionDto findByLabel(String label);
}
