package com.yeepay.g3.sdk.yop.client.basic;

import com.yeepay.g3.frame.yop.ca.utils.Encodes;
import com.yeepay.g3.sdk.yop.client.YopBaseClient;
import com.yeepay.g3.sdk.yop.client.YopConstants;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.enums.HttpMethodType;
import com.yeepay.g3.sdk.yop.unmarshaller.YopMarshallerUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;

/**
 * <pre>
 * 功能说明：易宝开放平台(YeepayOpenPlatform简称YOP)SDK客户端，简化用户发起请求及解析结果的处理，包括加解密
 * </pre>
 *
 * @author wang.bao
 * @version 1.0
 */
public class YopBasicClient extends YopBaseClient {

    protected static final Logger logger = Logger.getLogger(YopBasicClient.class);

    /**
     * 发起post请求，以YopResponse对象返回
     *
     * @param methodOrUri 目标地址或命名模式的method
     * @param request     客户端请求对象
     * @return 响应对象
     */
    public static YopResponse postBasic(String methodOrUri, YopBasicRequest request) {
        String content = postBasicForString(methodOrUri, request);
        YopResponse response = YopMarshallerUtils.unmarshal(content, request.getFormat(), YopResponse.class);
        return response;
    }

    /**
     * 发起post请求，以字符串返回
     *
     * @param methodOrUri 目标地址或命名模式的method
     * @param request     客户端请求对象
     * @return 字符串形式的响应
     */
    public static String postBasicForString(String methodOrUri, YopBasicRequest request) {
        String serverUrl = richRequest(HttpMethodType.POST, methodOrUri, request);
        logger.info("signature:" + request.getParamValue(YopConstants.SIGN));
        request.setAbsoluteURL(serverUrl);
        request.encoding();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

        String authorizationHeader = "";
        try {
            authorizationHeader = Encodes.encodeBase64((request.getAppKey() + ":" + request.getSecretKey()).getBytes("utf-8")).trim();
        } catch (UnsupportedEncodingException e) {
            logger.warn("", e);
        }

        headers.add("Authorization", "Basic " + authorizationHeader);
        headers.add("X-YOP-AppKey", request.getAppKey());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(request.getParams(), headers);

        String content = getRestTemplate(request).postForObject(serverUrl, httpEntity, String.class);
        if (logger.isDebugEnabled()) {
            logger.debug("response:\n" + content);
        }
        return content;
    }

}
