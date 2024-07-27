package ru.panyukovnn.linkshortener.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.panyukovnn.linkshortener.model.LinkInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class SortRequest {

    @NotEmpty
    private String field;

    @Pattern(regexp = "ASC|DESC")
    private String direction = "ASC";
}
