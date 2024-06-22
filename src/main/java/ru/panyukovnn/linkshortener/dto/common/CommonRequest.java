package ru.panyukovnn.linkshortener.dto.common;

import lombok.Data;

import javax.validation.Valid;

@Data
public class CommonRequest<T> {

    @Valid
    private T body;
}
