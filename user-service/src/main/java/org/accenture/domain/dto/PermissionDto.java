package org.accenture.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 17:57
 * @project: FLIGHTSTUFF
 */


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private Long id;
    @NotEmpty(message = "label can't be empty")
    private String label;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime created;
}
