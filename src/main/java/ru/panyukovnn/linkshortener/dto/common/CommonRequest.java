package ru.panyukovnn.linkshortener.dto.common;

import lombok.Data;

@Data
public class CommonRequest<T> {

    private T body;
}
