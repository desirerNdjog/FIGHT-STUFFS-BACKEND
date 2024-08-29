package org.accenture.mappers.user;

import org.accenture.domain.dto.UserDto;
import org.accenture.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 16:48
 * @project: FIGHTSTUFF
 */

@Service
public class UserToDto implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.isFirstLogin(),
                user.getUserstatus(),
                user.getRoles()
        );
    }
}
