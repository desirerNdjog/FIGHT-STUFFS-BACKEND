package org.accenture.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 22:02
 * @project: FIGHTSTUFF
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
public class HttpResponse {
    private int code;
    private String message;
    private HttpStatus httpStatus;
    private String date;
    private Map<?, ?> datas;
}
