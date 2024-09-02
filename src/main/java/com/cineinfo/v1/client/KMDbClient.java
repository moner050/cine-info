package com.cineinfo.v1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KMDbClient {

    public static String serviceKey;                        // API 서비스 인증키
    public static String basicUrl;                          // 기본요청 URL

    /******************************** application-kmdb 에 설정된 값 불러오기 ************************************/
    @Value("${kmdb.serviceKey}")
    public void setServiceKey(String key) {
        serviceKey = key;
    }

    @Value("${kmdb.url}")
    public void setBasicUrl(String url) {
        basicUrl = url;
    }

    /********************************************************************************************************/


}
