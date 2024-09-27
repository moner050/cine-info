package com.cineinfo.v1.dto.kofic.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FaultInfoRes {

    private String message;
    private String errorCode;
}