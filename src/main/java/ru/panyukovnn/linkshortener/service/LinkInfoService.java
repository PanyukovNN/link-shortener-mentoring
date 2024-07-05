package ru.panyukovnn.linkshortener.service;

import ru.panyukovnn.linkshortener.dto.CreateShortLinkRequest;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkResponse;
import ru.panyukovnn.linkshortener.model.LinkInfo;

import java.util.List;
import java.util.UUID;

public interface LinkInfoService {

    CreateShortLinkResponse createLinkInfo(CreateShortLinkRequest request);

    LinkInfo getByShortLink(String shortLink);

    void deleteById(UUID id);

    List<CreateShortLinkResponse> getAll();
}
