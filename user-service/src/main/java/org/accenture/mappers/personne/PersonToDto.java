package org.accenture.mappers.personne;

import org.accenture.domain.dto.PersonDto;
import org.accenture.domain.models.Personne;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 17:21
 * @project: FIGHTSTUFF
 */

@Service
public class PersonToDto implements Function<Personne, PersonDto> {
    @Override
    public PersonDto apply(Personne personne) {
        return new PersonDto(
                personne.getId(),
                personne.getFirstname(),
                personne.getMiddlename(),
                personne.getLastname(),
                personne.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );
    }
}
