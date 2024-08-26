package org.accenture.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.accenture.utils.PermissionEnum;

import java.io.Serializable;
import java.util.Set;


/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 15:14
 * @project: FIGHTSTUFF
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "can't be empty")
    private String label;
    @Column(nullable = false)
    @Transient
    private Set<PermissionEnum> permissions;
}
