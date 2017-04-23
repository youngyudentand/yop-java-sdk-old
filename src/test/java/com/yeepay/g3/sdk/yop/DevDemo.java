package com.yeepay.g3.sdk.yop;

import com.TrustAllHttpsCertificates;
import com.yeepay.g3.sdk.yop.client.YopClient;
import com.yeepay.g3.sdk.yop.client.YopClient3;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.encrypt.AESEncrypter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * title: <br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 15/7/8 10:23
 */
public class DevDemo {

    private static final String BASE_URL = "http://172.17.102.173:8064/yop-center/";

    private static final String[] APP_KEYS = {"yop-boss", "jinkela2"};
    private static final String[] APP_SECRETS = {"cGB2CeC3YmwSWGoVz0kAvQ==", "0/ZoyfKku0tunPunw7dbfA=="};

    @Before
    public void setUp() throws Exception {
        TrustAllHttpsCertificates.setTrue();
    }

    @Test
    public void testIdCard() throws Exception {
        int i=0;
        YopRequest request = new YopRequest(null, APP_SECRETS[i], BASE_URL);
//        YopRequest request = new YopRequest(null, "cAFj+DxhpeMo8afn7s0z5w==", "http://10.151.30.88:8064/yop-center/");
        request.setEncrypt(true);
        request.setSignRet(true);
        request.setSignAlg("sha-256");
        request.addParam("appKey", "jinkela2");
        request.addParam("requestFlowId", "test123456");//请求流水标识
        request.addParam("name", "张文康");
        request.addParam("idCardNumber", "370982199101186692");
        request.addParam("testDate", "2012-10-09 12:13:14");
        System.out.println(request.toQueryString());
        YopResponse response = YopClient.post("/rest/v2.0/auth/idcard", request);
        System.out.println(response.toString());
    }

    @Test
    public void testEnterprise() throws Exception {
        int i = 0;
        YopRequest request = new YopRequest(null, APP_SECRETS[i], BASE_URL);
        request.setEncrypt(true);
        request.setSignRet(true);
        request.addParam("appKey", APP_KEYS[i]);//这个写YOP就可以了
        request.addParam("corpName", "安徽四创电子股份有限公司青海分公司");//企业名称
        request.addParam("regNo", "630104063035716");//工商注册号
        request.addParam("requestCustomerId", "yop-boss");//子商户编号
        request.addParam("requestFlowId", "test-" + System.currentTimeMillis() + RandomStringUtils.randomNumeric(3));//请求流水标识
        request.addParam("requestIdentification", "wenkang.zhang");//请求者标识
        System.out.println(request.toQueryString());
        YopResponse response = YopClient.post("/rest/v2.2/auth/enterprise", request);
        System.out.println(response.toString());
    }

    @Test
    public void testWhiteList() throws Exception {
        YopRequest request = new YopRequest(null, "cGB2CeC3YmwSWGoVz0kAvQ==", BASE_URL);
        request.setEncrypt(false);
        request.setSignRet(true);
        request.addParam("appKey", "yop-boss");
        request.setSignAlg("sha-256");
        request.addParam("requestFlowId", "test123456");//请求流水标识
        request.addParam("name", "张文康");
        request.addParam("idCardNumber", "370982199101186691");
        System.out.println(request.toQueryString());
        YopResponse response = YopClient.post("/rest/v2.0/auth/idcard", request);
        System.out.println(response.toString());
    }

    @Test
    public void testCreateToken() {
        YopRequest request = new YopRequest("sopay", "RQbBAw3GmxOIjh0386owzA==", BASE_URL);
        request.setEncrypt(true);
        request.setSignRet(true);

        request.setSignAlg("sha-256");

        request.addParam("grant_type", "password");//请求流水标识
        request.addParam("client_id", "sopay");
        request.addParam("authenticated_user_id", "18310417069");
        System.out.println(request.toQueryString());
        YopResponse response = YopClient.post("/rest/v1.0/oauth2/token", request);
        System.out.println(response.toString());
    }

    @Test
    public void testRefreshToken() {
        YopRequest request = new YopRequest("sopay", "RQbBAw3GmxOIjh0386owzA==", BASE_URL);
        request.setEncrypt(true);
        request.setSignRet(true);

        request.setSignAlg("sha-256");

        request.addParam("refresh_token", "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJodHRwczovL29wZW4ueWVlcGF5LmNvbSIsImNpZCI6IjIxIiwidWlkIjoiMTgzMTA0MTcwNjkiLCJzY29wZSI6W10sInN1YiI6InJlZnJlc2hfdG9rZW4iLCJleHAiOjE0ODAxNTU0NjcsIm5vbmNlIjoiNTdmYzI1OGJiMDA4NGQxODkzZmQ3OGUzMzhkZjNmOTUifQ.yDmnJRLiQVQ344bRO3-Zd5pFnkXzZlK8WCHQgcSEqnY-CKRq0dL36UeJ9Rz8pUCr5e7nPxXrKLpIczZ2q0j3Sw");
        System.out.println(request.toQueryString());
        YopResponse response = YopClient.post("/rest/v1.0/oauth2/refresh_token", request);
        System.out.println(response.toString());
    }

    @Test
    public void m(){
        System.out.println(AESEncrypter.decrypt("mvxxwVMPE6QLXzU+hu+WCQ==","I am a fool, OK?"));
    }

    @Test
    public void yop(){
        try {
            TrustAllHttpsCertificates.setTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //int i = 0;
        String secretKey ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEk5fANXccim3575EasuANg_U_rM14xSNKi7KWdHRSY7hAJK7N-QhWOGz81rKLmgyd4Q7c1dmZbLzNnOQlGYjBAy2jH0a84EW5dM3GqNCX_A3iJda8xSUIpsaxOceqc46z370sijjVmn9rlbJMNpx-BZuLAHivYVOtg95-VOeWiyGMsDlMpAZQjD-bRWshMV41Bzlq-9u5h-NGJ7371wvrqSpAId2Jxp9OR-G8_nSByAvS3y2xliwbntK9MEcD64ew-6dkLb3hsmVc2pWm0uZPumnirjyvGatd--OrEAtb_8-Bki-ukqJWOAdb5bdp29gkg738Gdl-5at3e6uHdz0JAgMBAAECggEAYYJUmLA6PSmrnaqQJPzvQcGOfhjQv0TvogKBhZt9eqORfsv8Lc4-TXwO3R_kDj1tjilbzx0SgH-zld8RBiBzrtJxnIqCcqTZY3__YWAEm-RtKan--LRfeq9_cBY5PqrjiHTFJJ89Eg4iLbTagKeiDiZ9sozUNtn0u6hD2tMDynrU7pI9uyFIkPdU9ratku8tOgKWLFchRCQ1UD5Knda3F7fW0V4sCxfVqpuCZIROj7zAoB-RCMxkubiO6CyMZA19sunUQRwnp71DXcUqbZK-_jhef_hBQBUX1oaEojYtua4jx5p8xo9nP7jJeK-xqCj_CAoQF4LomezuGojgvxOoAQKBgQDSsCdKL_aFO7ONJSMgRGpNT9MGOMfKmAfPuELDOqRmJyTfbyR8TUiDYq74L3ffgjPIjZlJJM8m5gpCGalmsxRMUyvsDXE_bU2Zb1jlj_FFSdC2y5eTVfac-Ihp1qISm-t8EwvE0qzNK2xbG3G6ijy4WOtMoUh6FfZhzfmbxDgoiQKBgQChFtqYFu--khcyd26GVjxdDyPyiQfyAyqwdaWHqjyYad0sjEgZDaaAF_2qvrkSuD7kYULI9nVZv7kJmQu-T8owf38Hz931r9GaJdJJSTvexinJ54T0GpJdPsOUosHHgfPFvyetl1pxD05GMt88Z36KUUcZXVnoJxS9mo7HpoBQgQKBgAfJRspxF1U5LZuLwc6ReLQ-vPe_5XJRSAifMKhyZFz6GVzAiMKnQITKgtjdODrkXvGMehu_5n_zhHGI7T_EYn2nnTnuDT9g1LtU6B4jwbDj13jJ8WIajTCj5rayne6-IGfHdGnjt0slza1YSE2yiift8VQ1qa4JXb-jkxP0nnaxAoGAO4t4F9n6msXjnzr4dt2viHKNRhyS_ElhYULLgh9SMMCJCet8xw39qsGzeYbwYFQMo1y0VBaOADPXUQ3qgll6En0-VoPmtudbohAy7_YLFGjJj6FtytF7os4Ne4bB_F4z3revEgKtYrdWpqotTGWxJ62ti1mvXxn7F67m8jPAoIECgYEA0dGY2JnYVIku9hOYTTzjAJCRqA2Exl4lzxLYyD1SG_gTly9cee77m4wHOwYpLrRAu8zwLK5_4EkKDk_AKAVP9-lbqIWo7LG3KQ8OAbaJ3XnF4-ildPGQWXlpusDZQhYTFZIbbZ7zhi30A3XiZUqVMBkn8y1LdFmgj7Ogb4_87K4" ;

        //String BASE_URL = "http://10.151.30.80:18064/yop-center/";
//        String BASE_URL = "https://open.yeepay.com/yop-center/";
//        String BASE_URL = "https://58.83.141.56/yop-center/";


        String appKey = "OPR:10012481831" ;
        YopRequest request = new YopRequest(appKey, secretKey, BASE_URL);
        //YopRequest request = new YopRequest(appKey, "", BASE_URL);


        request.addParam("customerNo", "10012481831");
        request.addParam("parentCustomerNo", "10012481831");
        request.addParam("requestId", "requestId1480392119078");
        request.addParam("uniqueOrderNo", "1001201611290000000000000808");

        System.out.println(request.toQueryString());
        YopResponse response = YopClient3.postRsa("/rest/v2.0/opr/queryorder", request);
        System.out.println(response.toString());
    }

}
