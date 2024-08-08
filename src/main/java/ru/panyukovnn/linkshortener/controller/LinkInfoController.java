package ru.panyukovnn.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.panyukovnn.linkshortener.dto.*;
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
    public CommonResponse<LinkInfoResponse> postCreateShortLink(
            @RequestBody @Valid CommonRequest<CreateShortLinkRequest> request) {
        LinkInfoResponse linkInfoResponse = linkInfoService.createLinkInfo(request.getBody());

        return CommonResponse.<LinkInfoResponse>builder()
                .body(linkInfoResponse)
                .build();
    }

    @PostMapping("/filter")
    public CommonResponse<List<LinkInfoResponse>> filter(@RequestBody @Valid CommonRequest<FilterLinkInfoRequest> request) {
        List<LinkInfoResponse> linkInfoResponses = linkInfoService.findByFilter(request.getBody());

        return CommonResponse.<List<LinkInfoResponse>>builder()
            .body(linkInfoResponses)
            .build();
    }

    @PatchMapping
    public CommonResponse<LinkInfoResponse> patchUpdateShortLink(@RequestBody @Validated CommonRequest<UpdateShortLinkRequest> request) {
        LinkInfoResponse linkInfo = linkInfoService.updateLinkInfo(request.getBody());

        return CommonResponse.<LinkInfoResponse>builder()
            .body(linkInfo)
            .build();
    }

    @DeleteMapping
    public CommonResponse<?> deleteById(@RequestBody @Validated CommonRequest<IdRequest> request) {
        linkInfoService.deleteById(request.getBody().getId());

        return CommonResponse.builder()
            .build();
    }
}
