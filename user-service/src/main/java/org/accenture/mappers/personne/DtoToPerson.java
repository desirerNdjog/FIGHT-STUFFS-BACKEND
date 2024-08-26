package org.accenture.mappers.personne;

import org.accenture.domain.dto.PersonDto;
import org.accenture.domain.models.Personne;
import org.accenture.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 17:22
 * @project: FIGHTSTUFF
 */

@Service
public class DtoToPerson implements Function<PersonDto, Personne>{
    @Override
    public Personne apply(PersonDto personDto) {
        return new Personne(
                personDto.getId(),
                personDto.getFirstname(),
                personDto.getMiddlename(),
                personDto.getLastname(),
                DateUtils.stringDateToLocalDate(personDto.getBirthdate())
        );
    }
}
