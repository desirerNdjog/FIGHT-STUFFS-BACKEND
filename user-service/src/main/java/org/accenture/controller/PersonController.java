package org.accenture.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.accenture.domain.dto.PersonDto;
import org.accenture.http.HttpResponse;
import org.accenture.http.service.HttpService;
import org.accenture.service.PersonneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: desirejuniorndjog.
 * @created: 23/08/2024 : 21:23
 * @project: FLIGHTSTUFF
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonneService service;

    @GetMapping()
    public ResponseEntity<HttpResponse> allPersonPaginate(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size
    ){
        return HttpService.responseSuccess(
                Map.of("data", service.listPersonnePaginate(search, page, size))
        );
    }

    @GetMapping("/{id}/person")
    public ResponseEntity<HttpResponse> findById(@PathVariable(name = "id") Long id){
        PersonDto person = service.findById(id);
        if (person != null){
            return HttpService.responseSuccess(Map.of("data", person));
        }
        return HttpService.responseNotFound();
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid PersonDto personDto){
        service.create(personDto);
        return HttpService.responseOkSuccess();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> update(@PathVariable(name = "id") Long id, @RequestBody PersonDto personDto){
        PersonDto person = service.findById(id);
        if (person != null){
            person.setFirstname(personDto.getFirstname());
            person.setMiddlename(personDto.getMiddlename());
            person.setLastname(personDto.getLastname());
            return HttpService.responseSuccess(Map.of("data", service.update(person)));
        }
        return HttpService.responseNotFound();
    }

}
