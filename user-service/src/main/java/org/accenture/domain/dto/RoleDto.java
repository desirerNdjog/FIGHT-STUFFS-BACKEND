package org.accenture.domain.dto;

import lombok.*;
import org.accenture.utils.PermissionEnum;

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
public class RoleDto {
    private Long id;
    private String label;
    private Set<PermissionEnum> permissions;
}
