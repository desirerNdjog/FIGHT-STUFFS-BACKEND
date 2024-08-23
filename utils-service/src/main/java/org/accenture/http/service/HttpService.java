package org.accenture.http.service;


import org.accenture.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 22:02
 * @project: FIGHTSTUFF
 */

public class HttpService {

    private HttpService(){}

    public static ResponseEntity<HttpResponse> responseSuccess(Map<?, ?> data){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("retreive data")
                        .httpStatus(HttpStatus.OK)
                        .datas(data)
                        .date(LocalDateTime.now().toString())
                        .build()
        );
    }

    public static ResponseEntity<HttpResponse> responseOkSuccess(){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("created")
                        .httpStatus(HttpStatus.OK)
                        .date(LocalDateTime.now().toString())
                        .build()
        );
    }

    public static ResponseEntity<HttpResponse> responseNotFound(){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("not found")
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .date(LocalDateTime.now().toString())
                        .build()
        );
    }

    public static ResponseEntity<HttpResponse> responseInternalServerError(String message){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(message)
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .date(LocalDateTime.now().toString())
                        .build()
        );
    }
}
