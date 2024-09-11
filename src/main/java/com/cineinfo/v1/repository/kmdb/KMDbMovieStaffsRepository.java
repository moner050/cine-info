package com.cineinfo.v1.repository.kmdb;

import com.cineinfo.v1.domain.kmdb.KMDbMovieStaffs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KMDbMovieStaffsRepository extends JpaRepository<KMDbMovieStaffs, Long> {

    Optional<KMDbMovieStaffs> findByKmdbMovieInfo_MovieIdAndStaffNm(String movieId, String StaffNm);
    List<KMDbMovieStaffs> findByKmdbMovieInfo_MovieIdAndStaffRoleGroup(String movieId, String staffRoleGroup);
}
