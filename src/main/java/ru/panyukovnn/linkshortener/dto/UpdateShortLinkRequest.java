package ru.panyukovnn.linkshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShortLinkRequest {

    @NotNull(message = "Отсутствует идентификатор")
    private UUID id;
    private String link;
    private ZonedDateTime endTime;
    private String description;
    private Boolean active;
}
