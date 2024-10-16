package com.cineinfo.v1.controller.admin;


import com.cineinfo.util.FormDataEncoder;
import com.cineinfo.v1.dto.admin.DailyBoxOfficeRequest;
import com.cineinfo.v1.dto.admin.MovieInfoRequest;
import com.cineinfo.v1.dto.admin.WeeklyBoxOfficeRequest;
import com.cineinfo.v1.service.admin.KMDbApiService;
import com.cineinfo.v1.service.admin.KOFICApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("영화 및 박스오피스 관련 어드민 컨트롤러")
@Import(FormDataEncoder.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    private final MockMvc mvc;
    private final FormDataEncoder formDataEncoder;

    @MockBean private KOFICApiService koficApiService;
    @MockBean private KMDbApiService kmdbApiService;

    @Autowired
    public MovieControllerTest( MockMvc mvc, FormDataEncoder formDataEncoder) {
        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

    @DisplayName("[영화저장][POST] - 정상 응답")
    @Test
    public void givenMovieInfoRequestDTO_whenTryingToInsertMovieInfo_thenReturnHttpStatusOK() throws Exception{
        // given
        MovieInfoRequest movieInfoRequest = MovieInfoRequest.of("20240901", "20241016");
        given(kmdbApiService.saveAllKMDbMovieList(movieInfoRequest.releaseDts(), movieInfoRequest.releaseDte())).willReturn(true);

        // when&then
        mvc.perform(
                post("/v1/admin/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(formDataEncoder.encodeToJson(movieInfoRequest))
        )
        .andExpect(status().isOk());
    }

    @DisplayName("[일간 박스오피스 순위 저장][POST] - 정상 응답")
    @Test
    public void givenDailyBoxOffice_whenTryingToInsertDailyBoxOffice_thenReturnHttpStatusOK() throws Exception{
        // given
        DailyBoxOfficeRequest dailyBoxOfficeRequest = DailyBoxOfficeRequest.of("20241014", "20241016", "K");
        given(koficApiService.saveAllDailyBoxOffice(dailyBoxOfficeRequest.startDate(), dailyBoxOfficeRequest.endDate(), dailyBoxOfficeRequest.repNationCd())).willReturn(true);

        // when&then
        mvc.perform(
                        post("/v1/admin/boxOffice/daily")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(formDataEncoder.encodeToJson(dailyBoxOfficeRequest))
                )
        .andExpect(status().isOk());
    }

    @DisplayName("[주간 박스오피스 순위 저장][POST] - 정상 응답")
    @Test
    public void givenWeeklyBoxOffice_whenTryingToInsertWeeklyBoxOffice_thenReturnHttpStatusOK() throws Exception{
        // given
        WeeklyBoxOfficeRequest weeklyBoxOfficeRequest = WeeklyBoxOfficeRequest.of("20241014", "20241016", "K", "0");
        given(koficApiService.saveAllWeeklyBoxOffice(weeklyBoxOfficeRequest.startDate(), weeklyBoxOfficeRequest.endDate(), weeklyBoxOfficeRequest.repNationCd(), weeklyBoxOfficeRequest.weekGb())).willReturn(true);

        // when&then
        mvc.perform(
                        post("/v1/admin/boxOffice/weekly")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(formDataEncoder.encodeToJson(weeklyBoxOfficeRequest))
                )
        .andExpect(status().isOk());
    }
}
