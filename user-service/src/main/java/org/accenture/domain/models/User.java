package org.accenture.domain.models;

import jakarta.persistence.*;
import lombok.*;
import org.accenture.utils.UserStatusEnum;

import java.io.Serializable;
import java.util.Set;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 15:14
 * @project: FIGHTSTUFF
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean firstLogin;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserStatusEnum userstatus;
    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
}
