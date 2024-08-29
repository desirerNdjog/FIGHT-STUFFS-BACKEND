package org.accenture.auth.service;

import org.accenture.auth.dto.AuthLogin;
import org.accenture.auth.dto.AuthRegister;

/**
 * @author: desirejuniorndjog.
 * @created: 27/08/2024 : 18:40
 * @project: FLIGHTSTUFF
 */

public interface AuthService {
    String login(AuthLogin auth);
    String register(AuthRegister register);
    void forgotenPassword(String email);
    void firstConnexion(String email);
}
