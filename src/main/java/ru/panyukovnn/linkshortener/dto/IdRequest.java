package ru.panyukovnn.linkshortener.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IdRequest {

    @NotNull(message = "Идентификатор не может быть пустым")
    private UUID id;

}
