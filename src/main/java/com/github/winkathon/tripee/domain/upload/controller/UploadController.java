package com.github.winkathon.tripee.domain.upload.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.tripee.common.api.dto.response.ApiResponse;
import com.github.winkathon.tripee.common.security.util.UserContext;
import com.github.winkathon.tripee.domain.upload.dto.response.GetFileResponse;
import com.github.winkathon.tripee.domain.upload.response.UploadResponse;
import com.github.winkathon.tripee.domain.upload.service.UploadService;
import com.github.winkathon.tripee.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<UploadResponse> upload(@RequestParam("file") MultipartFile file) {

        User user = UserContext.getUser();

        return ApiResponse.ok(uploadService.upload(user, file));
    }

    @GetMapping("/{fileId}")
    @PreAuthorize("permitAll()")
    public ApiResponse<GetFileResponse> getFile(@PathVariable String fileId) {

        return ApiResponse.ok(uploadService.getFile(fileId));
    }
}
