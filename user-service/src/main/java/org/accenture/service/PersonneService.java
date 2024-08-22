package org.accenture.service;

import org.accenture.domain.dto.PersonDto;
import org.springframework.data.domain.Page;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 17:38
 * @project: FIGHTSTUFF
 */

public interface PersonneService {
    Page<PersonDto> listPersonnePaginate(String search, int page, int size);
    void create(PersonDto personDto);
    PersonDto update(PersonDto personDto);
    PersonDto findById(Long id);
}
