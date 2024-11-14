package com.sisa.pruebatenica.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ResponseDTO<T> {
    private Integer code;
    private String message;
    private T data;
}
