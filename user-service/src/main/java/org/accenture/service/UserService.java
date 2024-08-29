package org.accenture.service;

import org.accenture.domain.dto.UserDto;
import org.springframework.data.domain.Page;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 21:33
 * @project: FLIGHTSTUFF
 */
public interface UserService {
    Page<UserDto> allUserPaginate(String search, int page, int size);
    void create(UserDto userDto);
    UserDto update(UserDto userDto);
    UserDto findById(Long id);
    UserDto findByEmail(String email);
    UserDto findByUserName(String username);
}
