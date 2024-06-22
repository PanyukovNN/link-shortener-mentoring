package ru.panyukovnn.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkRequest;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkResponse;
import ru.panyukovnn.linkshortener.dto.common.CommonRequest;
import ru.panyukovnn.linkshortener.dto.common.CommonResponse;
import ru.panyukovnn.linkshortener.service.LinkInfoService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/link-infos")
public class LinkInfoController {

    private final LinkInfoService linkInfoService;

    @PostMapping
    public CommonResponse<CreateShortLinkResponse> postCreateShortLink(
            @RequestBody @Valid CommonRequest<CreateShortLinkRequest> request) {
        CreateShortLinkResponse createShortLinkResponse = linkInfoService.createLinkInfo(request.getBody());

        return CommonResponse.<CreateShortLinkResponse>builder()
                .body(createShortLinkResponse)
                .build();
    }
}
