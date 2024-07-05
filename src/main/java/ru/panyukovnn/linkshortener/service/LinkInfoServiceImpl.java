package ru.panyukovnn.linkshortener.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.panyukovnn.linkshortener.beanpostprocessor.LogExecutionTime;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkRequest;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkResponse;
import ru.panyukovnn.linkshortener.exception.NotFoundException;
import ru.panyukovnn.linkshortener.mapper.LinkInfoMapper;
import ru.panyukovnn.linkshortener.model.LinkInfo;
import ru.panyukovnn.linkshortener.property.LinkShortenerProperty;
import ru.panyukovnn.linkshortener.repository.LinkInfoRepository;

import java.util.List;
import java.util.UUID;

@Service
public class LinkInfoServiceImpl implements LinkInfoService {

    @Autowired
    private LinkInfoRepository repository;
    @Autowired
    private LinkInfoMapper linkInfoMapper;
    @Autowired
    private LinkShortenerProperty linkShortenerProperty;

    public LinkInfoServiceImpl() {
    }

    @LogExecutionTime
    public CreateShortLinkResponse createLinkInfo(CreateShortLinkRequest request) {
        LinkInfo linkInfo = linkInfoMapper.fromCreateRequest(request);
        linkInfo.setShortLink(RandomStringUtils.randomAlphanumeric(linkShortenerProperty.getShortLinkLength()));
        linkInfo.setOpeningCount(0L);

        repository.saveShortLink(linkInfo);

        return linkInfoMapper.toResponse(linkInfo);
    }

    @LogExecutionTime
    public LinkInfo getByShortLink(String shortLink) {
        return repository.findByShortLink(shortLink)
                .orElseThrow(() -> new NotFoundException("Не удалось найти длинную ссылку по короткой: " + shortLink));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<CreateShortLinkResponse> getAll() {
        return repository.getAll().stream()
            .map(linkInfoMapper::toResponse)
            .toList();
    }
}
