package com.cineinfo.v1.domain.kofic;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MovieCompaniesKey implements Serializable {

    private String movieCd;
    private String companyCd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieCompaniesKey movieCompaniesKey)) return false;
        return movieCd != null && companyCd != null && movieCd.equals(movieCompaniesKey.movieCd) && companyCd.equals(movieCompaniesKey.companyCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieCd, companyCd);
    }
}
