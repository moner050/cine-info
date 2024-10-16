package com.cineinfo.v1.controller.admin;

import com.cineinfo.v1.dto.admin.DailyBoxOfficeRequest;
import com.cineinfo.v1.dto.admin.MovieInfoRequest;
import com.cineinfo.v1.dto.admin.WeeklyBoxOfficeRequest;
import com.cineinfo.v1.service.admin.KMDbApiService;
import com.cineinfo.v1.service.admin.KOFICApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
public class MovieController {

    private final KMDbApiService kmdbApiService;
    private final KOFICApiService koficApiService;

    // 개봉일 기준 전체 영화 저장
    @PostMapping("/movies")
    public ResponseEntity insertMovieInfos(MovieInfoRequest movieInfoRequest) {
        kmdbApiService.saveAllKMDbMovieList(movieInfoRequest.releaseDts(), movieInfoRequest.releaseDte());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // 조회날짜 기준 전체 일별 박스오피스 순위 저장
    @PostMapping("/boxOffice/daily")
    public ResponseEntity insertDailyBoxOffice(DailyBoxOfficeRequest dailyBoxOfficeRequest) {
        koficApiService.saveAllDailyBoxOffice(dailyBoxOfficeRequest.startDate(), dailyBoxOfficeRequest.endDate(), dailyBoxOfficeRequest.repNationCd());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // 조회날짜 기준 전체 주간 박스오피스 순위 저장
    @PostMapping("/boxOffice/weekly")
    public ResponseEntity insertWeeklyBoxOffice(WeeklyBoxOfficeRequest weeklyBoxOfficeRequest) {
        koficApiService.saveAllWeeklyBoxOffice(weeklyBoxOfficeRequest.startDate(), weeklyBoxOfficeRequest.endDate(), weeklyBoxOfficeRequest.repNationCd(), weeklyBoxOfficeRequest.weekGb());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
