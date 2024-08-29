package org.accenture.mappers.user;

import org.accenture.domain.dto.UserDto;
import org.accenture.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 17:21
 * @project: FIGHTSTUFF
 */
@Service
public class DtoToUser implements Function<UserDto, User> {
    @Override
    public User apply(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.isFirstLogin(),
                userDto.getUserstatus(),
                userDto.getRoles()
        );
    }
}
