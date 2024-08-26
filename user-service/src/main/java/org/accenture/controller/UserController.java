package org.accenture.controller;

import lombok.RequiredArgsConstructor;
import org.accenture.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 15:16
 * @project: FIGHTSTUFF
 */

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

}
