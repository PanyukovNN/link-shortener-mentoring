package ru.panyukovnn.linkshortener.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateShortLinkRequest {

    @NotEmpty(message = "Ссылка не может быть пустой")
    @Size(min = 10, max = 4096, message = "Длина ссылки должна быть не меньше 10 и не больше 4096")
    @Pattern(regexp = "https?://.+\\..+", message = "Ссылка не соответствует паттерну url")
    private String link;
    @Future(message = "Дата окончания действия ссылки должна быть в будущем")
    private ZonedDateTime endTime;
    @NotEmpty(message = "Описание ссылки не может быть пустым")
    private String description;
    @NotNull(message = "Признак активности не может быть пустым")
    private Boolean active;

}
