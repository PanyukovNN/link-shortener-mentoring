package ru.panyukovnn.linkshortener.repository;

import ru.panyukovnn.linkshortener.dto.CreateShortLinkResponse;
import ru.panyukovnn.linkshortener.model.LinkInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LinkInfoRepository {

    Optional<LinkInfo> findByShortLink(String shortLint);

    LinkInfo saveShortLink(LinkInfo linkInfo);

    List<LinkInfo> getAll();

    void deleteById(UUID id);
}
