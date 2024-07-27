package ru.panyukovnn.linkshortener.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.panyukovnn.linkshortener.beanpostprocessor.LogExecutionTime;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkRequest;
import ru.panyukovnn.linkshortener.dto.FilterLinkInfoRequest;
import ru.panyukovnn.linkshortener.dto.LinkInfoResponse;
import ru.panyukovnn.linkshortener.dto.PageableRequest;
import ru.panyukovnn.linkshortener.exception.NotFoundPageException;
import ru.panyukovnn.linkshortener.mapper.LinkInfoMapper;
import ru.panyukovnn.linkshortener.model.LinkInfo;
import ru.panyukovnn.linkshortener.property.LinkShortenerProperty;
import ru.panyukovnn.linkshortener.repository.LinkInfoRepository;

import java.time.ZonedDateTime;
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
    public LinkInfoResponse createLinkInfo(CreateShortLinkRequest request) {
        LinkInfo linkInfo = linkInfoMapper.fromCreateRequest(request);
        linkInfo.setShortLink(RandomStringUtils.randomAlphanumeric(linkShortenerProperty.getShortLinkLength()));
        linkInfo.setOpeningCount(0L);

        repository.save(linkInfo);

        return linkInfoMapper.toResponse(linkInfo);
    }

    @LogExecutionTime
    public LinkInfo getByShortLink(String shortLink) {
        LinkInfo linkInfo = repository.findByShortLinkAndActiveTrueAndEndTimeIsAfter(shortLink, ZonedDateTime.now())
            .orElseThrow(() -> new NotFoundPageException("Не удалось найти длинную ссылку по короткой: " + shortLink));

        repository.incrementOpeningCountByShortLink(shortLink);

        return linkInfo;
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<LinkInfoResponse> findByFilter(FilterLinkInfoRequest filterRequest) {
        Pageable pageable = createPageable(filterRequest);

        return repository.findByFilter(
                filterRequest.getLinkPart(),
                filterRequest.getEndTimeFrom(),
                filterRequest.getEndTimeTo(),
                filterRequest.getDescriptionPart(),
                filterRequest.getActive(),
                pageable).stream()
            .map(linkInfoMapper::toResponse)
            .toList();
    }

    private static Pageable createPageable(FilterLinkInfoRequest filterRequest) {
        PageableRequest page = filterRequest.getPage();

        List<Sort.Order> orders = page.getSorts().stream()
            .map(sort -> new Sort.Order(Sort.Direction.fromString(sort.getDirection()), sort.getField()))
            .toList();

        Sort sort = CollectionUtils.isEmpty(orders)
            ? Sort.unsorted()
            : Sort.by(orders);

        return PageRequest.of(page.getNumber() - 1, page.getSize(), sort);
    }
}
