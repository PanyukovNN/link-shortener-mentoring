package ru.panyukovnn.linkshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkRequest;
import ru.panyukovnn.linkshortener.model.LinkInfo;
import ru.panyukovnn.linkshortener.service.LinkInfoService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class LinkShortenerApp {

    @Autowired
    private LinkInfoService linkInfoService;

    @PostConstruct
    public void pc() {
        LinkInfo linkInfo = linkInfoService.createLinkInfo(new CreateShortLinkRequest());

        System.out.println(linkInfoService.getByShortLink(linkInfo.getShortLink()).getShortLink());
    }

    public static void main(String[] args) {
        SpringApplication.run(LinkShortenerApp.class);
    }
}
