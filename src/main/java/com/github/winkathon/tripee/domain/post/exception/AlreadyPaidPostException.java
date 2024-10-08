package com.github.winkathon.tripee.domain.post.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.tripee.common.api.exception.ApiException;

public class AlreadyPaidPostException extends ApiException {

    public AlreadyPaidPostException() {

        super(HttpStatus.BAD_REQUEST, "이미 결제된 게시물입니다.");
    }
}
