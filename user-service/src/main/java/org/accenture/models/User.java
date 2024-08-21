package org.accenture.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.accenture.utils.UserStatusEnum;

import java.io.Serializable;

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
}
