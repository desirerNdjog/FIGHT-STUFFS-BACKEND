package org.accenture.service;

import org.accenture.domain.dto.RoleDto;
import org.springframework.data.domain.Page;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 09:49
 * @project: FLIGHTSTUFF
 */

public interface RoleService {
    Page<RoleDto> listRolePaginate(String search, int page, int size);
    void create(RoleDto roleDto);
    RoleDto update(RoleDto roleDto);
    RoleDto findById(Long id);
    RoleDto findByLabel(String label);
}
