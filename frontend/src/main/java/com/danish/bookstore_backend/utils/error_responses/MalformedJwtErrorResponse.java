package com.danish.bookstore_backend.utils.error_responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MalformedJwtErrorResponse {

    private String message;
    private long timestamp;
}
