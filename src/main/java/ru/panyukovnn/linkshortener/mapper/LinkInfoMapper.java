package ru.panyukovnn.linkshortener.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkRequest;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkResponse;
import ru.panyukovnn.linkshortener.model.LinkInfo;

@Component
@Mapper(componentModel = "spring")
public interface LinkInfoMapper {

    LinkInfo fromCreateRequest(CreateShortLinkRequest request);

    CreateShortLinkResponse toResponse(LinkInfo linkInfo);
}
