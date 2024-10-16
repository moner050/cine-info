package com.cineinfo.v1.dto.admin;

import java.time.LocalDate;

public record MovieStatDto(LocalDate statDate, Long audiAcc, Long salesAcc, String stat_source) {

    public static MovieStatDto of(LocalDate statDate, Long audiAcc, Long salesAcc, String stat_source) {
        return new MovieStatDto(statDate, audiAcc, salesAcc, stat_source);
    }
}