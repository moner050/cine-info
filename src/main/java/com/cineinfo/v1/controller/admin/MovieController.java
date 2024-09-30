package com.cineinfo.v1.controller.admin;

import com.cineinfo.v1.service.admin.KMDbApiService;
import com.cineinfo.v1.service.admin.KOFICApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
public class MovieController {

    private final KMDbApiService kmdbApiService;
    private final KOFICApiService koficApiService;

    // 개봉일 기준 전체 영화 저장
    @PostMapping("/movies/{releaseDts}/{releaseDte}")
    public ResponseEntity insertMovieInfos(@PathVariable String releaseDts, @PathVariable String releaseDte) {
        kmdbApiService.saveAllKMDbMovieList(releaseDts, releaseDte);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
