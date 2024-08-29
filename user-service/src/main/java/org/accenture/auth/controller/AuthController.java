package org.accenture.auth.controller;

import lombok.RequiredArgsConstructor;
import org.accenture.auth.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: desirejuniorndjog.
 * @created: 27/08/2024 : 19:06
 * @project: FLIGHTSTUFF
 */

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

}
