package ru.panyukovnn.linkshortener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkRequest;
import ru.panyukovnn.linkshortener.model.LinkInfo;

@Slf4j
public class LingInfoServiceLoggingProxy implements LinkInfoService {

    private LinkInfoService linkInfoService;

    public LingInfoServiceLoggingProxy(LinkInfoService linkInfoService) {
        this.linkInfoService = linkInfoService;
    }

    @Override
    public LinkInfo createLinkInfo(CreateShortLinkRequest request) {
        return linkInfoService.createLinkInfo(request);
    }

    @Override
    public LinkInfo getByShortLink(String shortLink) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            return linkInfoService.getByShortLink(shortLink);
        } finally {
            stopWatch.stop();

            log.info("Время выполнения метода getByShortLink: {} мс", stopWatch.getTotalTimeMillis());
        }
    }
}
