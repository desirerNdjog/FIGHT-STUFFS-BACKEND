package org.accenture.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.accenture.domain.dto.RoleDto;
import org.accenture.http.HttpResponse;
import org.accenture.http.service.HttpService;
import org.accenture.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 10:04
 * @project: FLIGHTSTUFF
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {
    private RoleService service;

    @GetMapping()
    public ResponseEntity<HttpResponse> allPersonPaginate(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size
    ){
        return HttpService.responseSuccess(
                Map.of("data", service.listRolePaginate(search, page, size))
        );
    }

    @GetMapping("/{id}/role")
    public ResponseEntity<HttpResponse> findById(@PathVariable(name = "id") Long id){
        RoleDto role = service.findById(id);
        if (role != null){
            return HttpService.responseSuccess(Map.of("data", role));
        }
        return HttpService.responseNotFound();
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid RoleDto roleDto){
        service.create(roleDto);
        return HttpService.responseOkSuccess();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> update(@PathVariable(name = "id") Long id, @RequestBody RoleDto roleDto){
        RoleDto role = service.findById(id);
        if (role != null){
            role.setLabel(roleDto.getLabel());
            return HttpService.responseSuccess(Map.of("data", service.update(role)));
        }
        return HttpService.responseNotFound();
    }
}
