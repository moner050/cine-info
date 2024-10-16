package com.cineinfo.v1.dto.admin;

public record MovieInfoRequest(String releaseDts, String releaseDte) {

    public static MovieInfoRequest of(String releaseDts, String releaseDte) {
        return new MovieInfoRequest(releaseDts, releaseDte);
    }
}
