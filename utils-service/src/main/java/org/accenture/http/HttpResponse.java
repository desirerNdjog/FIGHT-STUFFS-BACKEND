package org.accenture.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 22:02
 * @project: FIGHTSTUFF
 */

@Getter
@Setter
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
