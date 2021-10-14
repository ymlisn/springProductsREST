package com.example.desafiojavaspringboot.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusErrorResponse {

    private Integer status_code;
    private List<String> message;

    public StatusErrorResponse(Integer status_code, String messageError){
        this.status_code = status_code;
        this.message = Collections.singletonList(messageError);
    }
}
