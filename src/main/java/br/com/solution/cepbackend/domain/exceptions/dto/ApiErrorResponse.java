package br.com.solution.cepbackend.domain.exceptions.dto;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        int status,
        String message,
        LocalDateTime timeStamp
) {
}
