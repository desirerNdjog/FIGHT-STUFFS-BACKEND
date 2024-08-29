package org.accenture.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.accenture.domain.dto.UserDto;
import org.accenture.http.HttpResponse;
import org.accenture.http.service.HttpService;
import org.accenture.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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


    @GetMapping()
    public ResponseEntity<HttpResponse> allPersonPaginate(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size
    ){
        return HttpService.responseSuccess(
                Map.of("data", service.allUserPaginate(search, page, size))
        );
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<HttpResponse> findById(@PathVariable(name = "id") Long id){
        UserDto userDto  = service.findById(id);
        if (userDto != null){
            return HttpService.responseSuccess(Map.of("data", userDto));
        }
        return HttpService.responseNotFound();
    }

    @GetMapping("/{email}/email")
    public ResponseEntity<HttpResponse> findByEmail(@PathVariable(name = "email") String email){
        UserDto userDto  = service.findByEmail(email);
        if (userDto != null){
            return HttpService.responseSuccess(Map.of("data", userDto));
        }
        return HttpService.responseNotFound();
    }

    @GetMapping("/{username}/username")
    public ResponseEntity<HttpResponse> findByUsername(@PathVariable(name = "username") String username){
        UserDto userDto  = service.findByUserName(username);
        if (userDto != null){
            return HttpService.responseSuccess(Map.of("data", userDto));
        }
        return HttpService.responseNotFound();
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid UserDto roleDto){
        service.create(roleDto);
        return HttpService.responseOkSuccess();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> update(@PathVariable(name = "id") Long id, @RequestBody UserDto roleDto){
        UserDto role = service.findById(id);
        if (role != null){
            role.setUsername(roleDto.getUsername());
            return HttpService.responseSuccess(Map.of("data", service.update(role)));
        }
        return HttpService.responseNotFound();
    }
}
