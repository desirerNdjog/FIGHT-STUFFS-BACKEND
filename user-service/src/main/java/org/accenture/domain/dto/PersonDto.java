package org.accenture.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 17:30
 * @project: FIGHTSTUFF
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Serializable {
    private Long id;
    @NotBlank(message = "firstname can't be empty")
    private String firstname;
    private String middlename;
    @NotBlank(message = "firstname can't be empty")
    private String lastname;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private String birthdate;
}
