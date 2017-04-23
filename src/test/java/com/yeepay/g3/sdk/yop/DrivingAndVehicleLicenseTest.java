package com.yeepay.g3.sdk.yop;

import com.yeepay.g3.sdk.yop.client.YopClient;
import com.yeepay.g3.sdk.yop.client.YopConfig;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import org.junit.Test;

/**
 * title: <br>
 * description:描述<br>
 * Copyright: Copyright (c)2011<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author guoliang.li
 * @version 1.0.0ß
 * @since 16/8/23 下午3:54
 */
public class DrivingAndVehicleLicenseTest {

    @Test
    public void brandTest() {
        YopConfig.setAppKey("test");//yop应用
        YopConfig.setAesSecretKey("LVLDflZNINrrCFPIis9gCA==");//yop应用密钥，需要和短信通知应用的密钥保持一致才行，否则验证签名不通过
        YopConfig.setServerRoot("http://172.17.102.173:8064/yop-center");
        YopRequest request = new YopRequest();
        request.setSignAlg("SHA-256");//具体看api签名算法而定
        request.setEncrypt(false);
//        request.addParam("appId","1b032989-6271-4603-960f-6e0d003143f4");
//        request.addParam("tokenId","76760ad6-8492-43d1-8823-24269d48c286");
        request.addParam("vin", "LVSHGFAR7FF087581");
//        request.addParam("plate","浙CH833R");
//        request.addParam("engine","FA01587");
        YopResponse response = YopClient.post("/rest/v1.0/auth/checkBrand", request);
        System.out.println(response);
    }

    @Test
    public void orderIdTest() throws Exception {
        YopConfig.setAppKey("test");//yop应用
        YopConfig.setAesSecretKey("LVLDflZNINrrCFPIis9gCA==");//yop应用密钥，需要和短信通知应用的密钥保持一致才行，否则验证签名不通过
        YopConfig.setServerRoot("http://172.17.102.173:8064/yop-center");
        YopRequest request = new YopRequest();
        request.setSignAlg("SHA-256");//具体看api签名算法而定
        request.setEncrypt(false);
//        request.addParam("appId","1b032989-6271-4603-960f-6e0d003143f4");
//        request.addParam("tokenId","76760ad6-8492-43d1-8823-24269d48c286");
        request.addParam("vin", "LVSHGFAR7FF087581");
        request.addParam("plate", "浙CH833R");
        request.addParam("engine", "FA01587");
        YopResponse response = YopClient.post("/rest/v1.0/auth/getOrderId", request);
        System.out.println(response);
    }

    @Test
    public void reportTest() {
        YopConfig.setAppKey("test");//yop应用
        YopConfig.setAesSecretKey("LVLDflZNINrrCFPIis9gCA==");//yop应用密钥，需要和短信通知应用的密钥保持一致才行，否则验证签名不通过
        YopConfig.setServerRoot("http://172.17.102.173:8064/yop-center");
        YopRequest request = new YopRequest();
        request.setSignAlg("SHA-256");//具体看api签名算法而定
        request.setEncrypt(false);
        request.addParam("orderId", "51bfee75f620413b9398296603d8b883");
        YopResponse response = YopClient.post("/rest/v1.0/auth/getReport", request);
        System.out.println(response);
    }

    @Test
    public void drivingAuth() throws Exception {
        YopConfig.setAppKey("test");//yop应用
        YopConfig.setAesSecretKey("LVLDflZNINrrCFPIis9gCA==");//yop应用密钥，需要和短信通知应用的密钥保持一致才行，否则验证签名不通过
        YopConfig.setServerRoot("http://172.17.102.173:8064/yop-center");
        YopRequest request = new YopRequest();
        request.setSignAlg("SHA-256");//具体看api签名算法而定
        request.setEncrypt(false);
        request.addParam("licenseNumber", "360121198605075212");
        request.addParam("name", "万汉波");
        request.addParam("requestSystem", "auth2-boss");
        request.addParam("requestCustomerId", "guoliang.li");
        request.addParam("requestFlowId", System.currentTimeMillis() + "");
        request.addParam("requestIP", "172.0.0.1");
        request.addParam("requestIdentification", "guoliang.li");
        YopResponse response = YopClient.post("/rest/v1.0/auth/drivingLicense", request);
        System.out.println(response);
    }

    @Test
    public void vehicleLicense() throws Exception {
        YopConfig.setAppKey("test");//yop应用
        YopConfig.setAesSecretKey("LVLDflZNINrrCFPIis9gCA==");//yop应用密钥，需要和短信通知应用的密钥保持一致才行，否则验证签名不通过
        YopConfig.setServerRoot("http://172.17.102.173:8064/yop-center");
        YopRequest request = new YopRequest();
        request.setSignAlg("SHA-256");//具体看api签名算法而定
        request.setEncrypt(false);
        request.addParam("plateNumber", "赣A40Q03");
        request.addParam("engineNumber", "BA964319");
        request.addParam("owner", "万汉波");
        request.addParam("ownerIdentifyNumber", "360121198605075212");
        request.addParam("licenseCategory", "PERSONAL_VEHICLE_LICENSE");
        request.addParam("requestFlowId", System.currentTimeMillis() + "");
        request.addParam("requestIP", "127.0.0.1");
        request.addParam("requestIdentification", "guoliang.li");
        YopResponse response = YopClient.post("/rest/v1.0/auth/vehicle-license", request);
        System.out.println(response);
    }

}

