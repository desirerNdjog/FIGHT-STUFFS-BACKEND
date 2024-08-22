package org.accenture.domain.models;

import
        com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
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
public class Personne implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "firstname can't be empty")
    private String firstname;
    @Column()
    private String middlename;
    @Column(nullable = false)
    @NotBlank(message = "firstname can't be empty")
    private String lastname;
    @JsonFormat(pattern = "dd/mm/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate birthdate;
}
