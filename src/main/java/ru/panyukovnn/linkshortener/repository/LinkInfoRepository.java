package ru.panyukovnn.linkshortener.repository;

import ru.panyukovnn.linkshortener.model.LinkInfo;

import java.util.Optional;

public interface LinkInfoRepository {

    Optional<LinkInfo> findByShortLink(String shortLint);

    LinkInfo saveShortLink(LinkInfo linkInfo);
}
