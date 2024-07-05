package ru.panyukovnn.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkRequest;
import ru.panyukovnn.linkshortener.dto.CreateShortLinkResponse;
import ru.panyukovnn.linkshortener.dto.IdRequest;
import ru.panyukovnn.linkshortener.dto.common.CommonRequest;
import ru.panyukovnn.linkshortener.dto.common.CommonResponse;
import ru.panyukovnn.linkshortener.service.LinkInfoService;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public CommonResponse<List<CreateShortLinkResponse>> getAll() {
        List<CreateShortLinkResponse> linkInfoResponses = linkInfoService.getAll();

        return CommonResponse.<List<CreateShortLinkResponse>>builder()
            .body(linkInfoResponses)
            .build();
    }

    @DeleteMapping
    public CommonResponse<?> deleteById(@RequestBody @Validated CommonRequest<IdRequest> request) {
        linkInfoService.deleteById(request.getBody().getId());

        return CommonResponse.builder()
            .build();
    }
}
