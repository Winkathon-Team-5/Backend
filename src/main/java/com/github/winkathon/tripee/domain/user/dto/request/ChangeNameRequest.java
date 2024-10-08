package com.github.winkathon.tripee.domain.user.dto.request;

import jakarta.validation.constraints.Pattern;

public record ChangeNameRequest(

        @Pattern(regexp = "^[가-힣]{2,4}$")
        String name
) {
}
