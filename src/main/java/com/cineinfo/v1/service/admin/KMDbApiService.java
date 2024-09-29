package com.cineinfo.v1.service.admin;

import com.cineinfo.v1.client.KMDbClient;
import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieStaffs;
import com.cineinfo.v1.dto.admin.kmdb.response.SearchKMDbMovieListRes;
import com.cineinfo.v1.dto.admin.kmdb.response.movie_list.DataRes;
import com.cineinfo.v1.dto.admin.kmdb.response.movie_list.ResultRes;
import com.cineinfo.v1.repository.admin.kmdb.*;
import com.cineinfo.v1.repository.kmdb.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KMDbApiService {

    private final KMDbClient kmdbClient;
    private final KMDbMovieInfoRepository kmdbMovieInfoRepository;
    private final KMDbMoviePlotsRepository kmdbMoviePlotsRepository;
    private final KMDbMoviePostersRepository kmdbMoviePostersRepository;
    private final KMDbMovieStaffsRepository kmdbMovieStaffsRepository;
    private final KMDbMovieStillsRepository kmdbMovieStillsRepository;
    private final KMDbMovieVodsRepository kmdbMovieVodsRepository;

    // 영화 상세 정보 검색 및 전체 저장
    public boolean saveAllKMDbMovieList(String releaseDts, String releaseDte) {
        int count = 1;
        int totalCount = saveKMDbMovieList("0", "500", releaseDts, releaseDte);

        if(totalCount > 500) {
            for (int i = 500; i <= totalCount; i+=500, count++) {
                saveKMDbMovieList(String.valueOf(i), "500", releaseDts, releaseDte);
            }
        }

        log.info("totalCount: " + totalCount);
        log.info("count: " + count);

        return true;
    }

    // 영화 상세 정보 검색 및 저장
    @Transactional
    public int saveKMDbMovieList(String startCount, String listCount, String releaseDts, String releaseDte) {
        SearchKMDbMovieListRes searchKMDbMovieList = kmdbClient.searchKMDbMovieList(startCount, listCount, releaseDts, releaseDte);

        int totalCount = searchKMDbMovieList.getTotalCount();

        log.info("TotalCount: " + totalCount);
        log.info("data size: " + searchKMDbMovieList.getData().size());

        DataRes dataRes = searchKMDbMovieList.getData().get(0);

        log.info("TotalCount: " + dataRes.getTotalCount());
        log.info("Count: " + dataRes.getCount());

        for (ResultRes resultRes : dataRes.getResult()) {

            // 해당 영화 정보가 이미 있으면 건너뛰기
            if(kmdbMovieInfoRepository.existsById(resultRes.getDOCID())) {
                continue;
            }

            // KMDb 영화 정보 저장
            KMDbMovieInfo savedEntity = kmdbMovieInfoRepository.save(resultRes.toEntity());

            // KMDb 영화 줄거리 저장
            int plotsListSize = resultRes.getPlots().getPlot().size();
            for (int i = 0; i < plotsListSize; i++) {
                log.info("plot count : " + (i+1));
                kmdbMoviePlotsRepository.save(resultRes.getPlots().getPlot().get(i).toEntity(savedEntity));
            }

            // KMDb 영화 스태프 정보 저장
            int staffsListSize = resultRes.getStaffs().getStaff().size();
            for (int i = 0; i < staffsListSize; i++) {
                log.info("staff count : " + (i+1));

                // 스태프가 이미 있으면 기존 스태프 데이터 업데이트
                Optional<KMDbMovieStaffs> searchStaff = kmdbMovieStaffsRepository
                        .findByKmdbMovieInfo_MovieIdAndStaffNm(savedEntity.getMovieId(), resultRes.getStaffs().getStaff().get(i).getStaffNm());

                if(searchStaff.isPresent()) {
                    KMDbMovieStaffs savedMovieStaff = searchStaff.get();
                    String newRoleGroup = savedMovieStaff.getStaffRoleGroup() + "," + resultRes.getStaffs().getStaff().get(i).getStaffRoleGroup();
                    String newRole = savedMovieStaff.getStaffRole().isBlank() ? "" : (savedMovieStaff.getStaffRole() + ",");
                    newRole += resultRes.getStaffs().getStaff().get(i).getStaffRole();
                    String newEtc = savedMovieStaff.getStaffEtc().isBlank() ? "" : (savedMovieStaff.getStaffEtc() + ",");
                    newEtc += resultRes.getStaffs().getStaff().get(i).getStaffEtc();


                    KMDbMovieStaffs newMovieStaffEntity = KMDbMovieStaffs.builder()
                            .staffId(savedMovieStaff.getStaffId())
                            .kmdbMovieInfo(savedEntity)
                            .staffNm(savedMovieStaff.getStaffNm())
                            .staffEnNm(savedMovieStaff.getStaffEnNm())
                            .staffRoleGroup(newRoleGroup)
                            .staffRole(newRole)
                            .staffEtc(newEtc)
                            .build();

                    kmdbMovieStaffsRepository.save(newMovieStaffEntity);
                }
                else {
                    kmdbMovieStaffsRepository.save(resultRes.getStaffs().getStaff().get(i).toEntity(savedEntity));
                }
            }

            String[] posters = resultRes.getPosters().split("\\|");
            String[] stills = resultRes.getStlls().split("\\|");

            // KMDb 영화 포스터 URL 링크 정보 저장
            for (String url : posters) {
                log.info("poster url : " + url);
                kmdbMoviePostersRepository.save(resultRes.toPosterEntity(savedEntity, url));
            }

            // KMDb 영화 스틸컷 URL 링크 정보 저장
            for (String url : stills) {
                log.info("stills url : " + url);
                kmdbMovieStillsRepository.save(resultRes.toStillsEntity(savedEntity, url));
            }

            // KMDb 영화 VOD 정보 저장
            int vodsListSize = resultRes.getVods().getVod().size();
            for (int i = 0; i < vodsListSize; i++) {
                log.info("vod count : " + (i+1));
                kmdbMovieVodsRepository.save(resultRes.getVods().getVod().get(i).toEntity(savedEntity));
            }

        }

        return totalCount;
    }
}
