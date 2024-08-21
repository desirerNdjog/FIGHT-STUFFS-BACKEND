package org.accenture.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 15:22
 * @project: FIGHTSTUFF
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private LocalDate birthdate;
}
