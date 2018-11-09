package com.yungui.utils;


import static java.lang.Class.forName;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.yung.common.DubboRequest;
import com.yung.common.DubboResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by  on 2017/10/23.
 */

public class DubboCallbackUtil {

    private static Logger logger = LoggerFactory.getLogger(DubboCallbackUtil.class);

    // 褰撳墠搴旂敤鐨勪俊鎭�
    private static ApplicationConfig application = new ApplicationConfig();
    // 娉ㄥ唽涓績淇℃伅缂撳瓨
    private static Map<String, RegistryConfig> registryConfigCache = new ConcurrentHashMap<>();

    // 鍚勪釜涓氬姟鏂圭殑ReferenceConfig缂撳瓨
    private static Map<String, ReferenceConfig> referenceCache = new ConcurrentHashMap<>();

    static {
        application.setName("consumer-test");
    }

    /**
     * 鑾峰彇娉ㄥ唽涓績淇℃伅
     *
     * @param address zk娉ㄥ唽鍦板潃
     * @param group   dubbo鏈嶅姟鎵�鍦ㄧ殑缁�
     * @return
     */
    private static RegistryConfig getRegistryConfig(String address, String group, String version) {
        String key = address + "-" + group + "-" + version;
        RegistryConfig registryConfig = registryConfigCache.get(key);
        if (null == registryConfig) {
            registryConfig = new RegistryConfig();
            if (StringUtils.isNotEmpty(address)) {
                registryConfig.setAddress(address);
            }
            if (StringUtils.isNotEmpty(version)) {
                registryConfig.setVersion(version);
            }
            if (StringUtils.isNotEmpty(group)) {
                registryConfig.setGroup(group);
            }
            registryConfigCache.put(key, registryConfig);
        }
        return registryConfig;
    }

    private static ReferenceConfig getReferenceConfig(String interfaceName, String address,
                                                      String group, String version) {
        String referenceKey = interfaceName;

        ReferenceConfig referenceConfig = referenceCache.get(referenceKey);
        if (null == referenceConfig) {
            referenceConfig = new ReferenceConfig<GenericService>();
            referenceConfig.setApplication(application);
            referenceConfig.setRegistry(getRegistryConfig(address, group, version));
            referenceConfig.setInterface(interfaceName);
            if (StringUtils.isNotEmpty(version)) {
                referenceConfig.setVersion(version);
            }
            referenceConfig.setGeneric(true);
            //referenceConfig.setUrl("dubbo://10.1.50.167:20880/com.test.service.HelloService");
            referenceCache.put(referenceKey, referenceConfig);
        }
        return referenceConfig;
    }

    public static Object invoke(String interfaceName, String methodName, List<Object> paramList, String address, String version) {
        ReferenceConfig reference = getReferenceConfig(interfaceName, address, null, version);
        if (null != reference) {
            GenericService genericService = (GenericService) reference.get();
            if (genericService == null) {
                logger.debug("GenericService 涓嶅瓨鍦�:{}", interfaceName);
                return null;
            }

            Object[] paramObject = null;
            if (paramList!=null &&paramList.size()>0) {
                paramObject = new Object[paramList.size()];
                for (int i = 0; i < paramList.size(); i++) {
                    paramObject[i] = paramList.get(i);
                }
            }


            Object resultParam = genericService.$invoke(methodName, new String[]{"com.yung.common.DubboRequest"}, paramObject);
            return resultParam;
        }
        return null;
    }

    public static Object invoke(String interfaceName, String methodName, DubboRequest request, String address, String version) {
        ReferenceConfig reference = getReferenceConfig(interfaceName, address, null, version);
        if (null != reference) {
            GenericService genericService = (GenericService) reference.get();
            if (genericService == null) {
                logger.debug("GenericService 涓嶅瓨鍦�:{}", interfaceName);
                return null;
            }
            Object result = genericService.$invoke(methodName, new String[]{"com.yung.common.DubboRequest"},new Object[]{request});
            return result;
        }
        return null;
    }


    public static String[] getMethodParamType(String interfaceName, String methodName) {
        try {
            //鍒涘缓绫�
            Class<?> class1 = Class.forName(interfaceName);
            //鑾峰彇鎵�鏈夌殑鍏叡鐨勬柟娉�
            Method[] methods = class1.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] paramClassList = method.getParameterTypes();
                    String[] paramTypeList = new String[paramClassList.length];
                    int i = 0;
                    for (Class className : paramClassList) {
                        paramTypeList[i] = className.getTypeName();
                        i++;
                    }
                    return paramTypeList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}