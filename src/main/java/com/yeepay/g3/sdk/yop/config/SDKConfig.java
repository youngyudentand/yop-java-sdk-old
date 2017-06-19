package com.yeepay.g3.sdk.yop.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Map;

/**
 * title: <br>
 * description:描述<br>
 * Copyright: Copyright (c)2011<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2016/12/26 下午3:50
 */
public final class SDKConfig implements Serializable {

    private static final long serialVersionUID = -6377916283927611130L;

    @JsonProperty("app_key")
    private String appKey;

    @JsonProperty("aes_secret_key")
    private String aesSecretKey;

    @JsonProperty("server_root")
    private String serverRoot;

    @JsonProperty("yop_public_key")
    private CertConfig[] yopPublicKey;

    @JsonProperty("isv_private_key")
    private CertConfig[] isvPrivateKey;

    @JsonProperty("api")
    private Map<String, ApiConfig> apiConfig;

    @JsonProperty("connect_timeout")
    private Integer connectTimeout;

    @JsonProperty("read_timeout")
    private Integer readTimeout;

    @JsonProperty("trust_certificate")
    private CertificateConfig trustCertificate;

    @JsonProperty("client_certificate")
    private CertificateConfig clientCertificate;

    public CertConfig[] getYopPublicKey() {
        return yopPublicKey;
    }

    public void setYopPublicKey(CertConfig[] yopPublicKey) {
        this.yopPublicKey = yopPublicKey;
    }

    public CertConfig[] getIsvPrivateKey() {
        return isvPrivateKey;
    }

    public void setIsvPrivateKey(CertConfig[] isvPrivateKey) {
        this.isvPrivateKey = isvPrivateKey;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getServerRoot() {
        return serverRoot;
    }

    public void setServerRoot(String serverRoot) {
        this.serverRoot = serverRoot;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAesSecretKey() {
        return aesSecretKey;
    }

    public void setAesSecretKey(String aesSecretKey) {
        this.aesSecretKey = aesSecretKey;
    }

    public CertificateConfig getTrustCertificate() {
        return trustCertificate;
    }

    public void setTrustCertificate(CertificateConfig trustCertificate) {
        this.trustCertificate = trustCertificate;
    }

    public CertificateConfig getClientCertificate() {
        return clientCertificate;
    }

    public void setClientCertificate(CertificateConfig clientCertificate) {
        this.clientCertificate = clientCertificate;
    }

    public Map<String, ApiConfig> getApiConfig() {
        return apiConfig;
    }

    public void setApiConfig(Map<String, ApiConfig> apiConfig) {
        this.apiConfig = apiConfig;
    }
}