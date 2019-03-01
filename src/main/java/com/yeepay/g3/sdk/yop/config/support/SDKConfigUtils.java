package com.yeepay.g3.sdk.yop.config.support;

import com.yeepay.g3.sdk.yop.YopServiceException;
import com.yeepay.g3.sdk.yop.config.SDKConfig;
import com.yeepay.g3.sdk.yop.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * title: sdk配置支持类<br>
 * description:描述<br>
 * Copyright: Copyright (c)2011<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2016/12/26 下午3:50
 */
public final class SDKConfigUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SDKConfigUtils.class);

    public static SDKConfig loadConfig(String configFile) {
        InputStream fis = null;
        SDKConfig config;
        try {
            fis = ConfigUtils.getInputStream(configFile);
            config = JsonUtils.loadFrom(fis, SDKConfig.class);
        } catch (Exception ex) {
            LOGGER.error("Errors occurred when loading SDKConfig,configFile:" + configFile, ex);
            throw new YopServiceException(ex, "Errors occurred when loading SDK Config.");
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
        if (StringUtils.endsWith(config.getServerRoot(), "/")) {
            config.setServerRoot(StringUtils.substring(config.getServerRoot(), 0, -1));
        }
        if (StringUtils.endsWith(config.getYosServerRoot(), "/")) {
            config.setYosServerRoot(StringUtils.substring(config.getYosServerRoot(), 0, -1));
        }
        return config;
    }

    public static SDKConfig loadConfig(InputStream in) {
        InputStream fis = null;
        SDKConfig config;
        try {
            config = JsonUtils.loadFrom(in, SDKConfig.class);
        } catch (Exception ex) {
            throw new YopServiceException(ex, "Errors occurred when loading SDK Config.");
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
        if (StringUtils.endsWith(config.getServerRoot(), "/")) {
            config.setServerRoot(StringUtils.substring(config.getServerRoot(), 0, -1));
        }
        return config;
    }

}

