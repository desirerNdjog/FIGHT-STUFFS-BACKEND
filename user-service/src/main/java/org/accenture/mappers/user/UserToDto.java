package org.accenture.mappers.user;

import org.accenture.domain.dto.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 16:48
 * @project: FIGHTSTUFF
 */

@Service
public class UserToDto implements Function<User, UserToDto> {
    @Override
    public UserToDto apply(User user) {
        return null;
    }
}
