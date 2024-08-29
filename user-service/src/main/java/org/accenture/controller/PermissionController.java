package org.accenture.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.accenture.domain.dto.PermissionDto;
import org.accenture.http.HttpResponse;
import org.accenture.http.service.HttpService;
import org.accenture.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 18:17
 * @project: FLIGHTSTUFF
 */

@RestController
@RequestMapping("/api/v1/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService service;

    @GetMapping()
    public ResponseEntity<HttpResponse> allPersonPaginate(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size
    ){
        return HttpService.responseSuccess(
                Map.of("data", service.listPermissionPaginate(search, page, size))
        );
    }

    @GetMapping("/{id}/permission")
    public ResponseEntity<HttpResponse> findById(@PathVariable(name = "id") Long id){
        PermissionDto permission = service.findById(id);
        if (permission != null){
            return HttpService.responseSuccess(Map.of("data", permission));
        }
        return HttpService.responseNotFound();
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid PermissionDto permissionDto){
        service.create(permissionDto);
        return HttpService.responseOkSuccess();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> update(@PathVariable(name = "id") Long id, @RequestBody PermissionDto permissionDto){
        PermissionDto permission = service.findById(id);
        if (permission != null){
            permission.setLabel(permissionDto.getLabel());
            return HttpService.responseSuccess(Map.of("data", service.update(permission)));
        }
        return HttpService.responseNotFound();
    }
}
