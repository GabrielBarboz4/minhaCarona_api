package com.minhaCarona.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CarpoolData(
        String tenantName,
        Long carId,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startCarpool,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate finalCarpool
) {}
