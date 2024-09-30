package com.cineinfo.v1.dto.admin.kofic.response;

import com.cineinfo.v1.dto.admin.kofic.response.comcode.CodesRes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchKOFICCodeListRes {

    private List<CodesRes> codes;
    private FaultInfoRes faultInfo;
}
