package ru.panyukovnn.linkshortener.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkRequest;
import ru.panyukovnn.linkshortener.exception.NotFoundException;
import ru.panyukovnn.linkshortener.model.LinkInfo;
import ru.panyukovnn.linkshortener.repository.LinkInfoRepository;

@Service
@RequiredArgsConstructor
public class LinkInfoServiceImpl implements LinkInfoService {

    private final LinkInfoRepository repository;

    @Value("${link-shortener.short-link-length}")
    private int shortLinkLength;

    public LinkInfo createLinkInfo(CreateShortLinkRequest request) {
        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setLink(request.getLink());
        linkInfo.setEndTime(request.getEndTime());
        linkInfo.setDescription(request.getDescription());
        linkInfo.setActive(request.getActive());
        linkInfo.setShortLink(RandomStringUtils.randomAlphanumeric(shortLinkLength));
        linkInfo.setOpeningCount(0L);

        return repository.saveShortLink(linkInfo);
    }

    public LinkInfo getByShortLink(String shortLink) {
        return repository.findByShortLink(shortLink)
                .orElseThrow(() -> new NotFoundException("Не удалось найти длинную ссылку по короткой: " + shortLink));
    }
}
