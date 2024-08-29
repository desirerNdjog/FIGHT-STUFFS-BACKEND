package org.accenture.auth.dao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.accenture.UserService;
import org.accenture.auth.dto.AuthLogin;
import org.accenture.auth.dto.AuthRegister;
import org.accenture.auth.service.AuthService;
import org.springframework.stereotype.Repository;

/**
 * @author: desirejuniorndjog.
 * @created: 27/08/2024 : 19:09
 * @project: FLIGHTSTUFF
 */

@Repository
@Transactional
@RequiredArgsConstructor
public class AuthDao implements AuthService {
    private final UserService service;
    @Override
    public String login(AuthLogin auth) {
        return "";
    }

    @Override
    public String register(AuthRegister register) {
        return "";
    }

    @Override
    public void forgotenPassword(String email) {
        //todo
    }

    @Override
    public void firstConnexion(String email) {
        //todo
    }
}
