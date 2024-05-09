package com.akali.toolset.model.word;

import lombok.Data;

import java.util.List;

/**
 * Description: word文档中提取的接口信息
 *
 * @author Jian-Jun Duan
 * @date 2024/5/8 17:25
 */
@Data
public class InterfaceInfo {
    /**
     * 接口名称
     */
    private String interfaceName;
    /**
     * 接口说明
     */
    private String interfaceDesc;
    /**
     * 本地接口编码
     */
    private String localInterfaceCode;
    /**
     * 服务英文名
     */
    private String serviceEnName;
    /**
     * URL
     */
    private String url;
    /**
     * HTTP方法
     */
    private String httpMethod;
    /**
     * 接口类型
     */
    private String interfaceType;
    /**
     * 请求参数
     */
    private List<ParameterInfo> requestParameters;
    /**
     * 请求报文示例
     */
    private String requestMessageExample;
    /**
     * 响应参数
     */
    private List<ParameterInfo> responseParameters;
    /**
     * 响应成功报文示例
     */
    private String responseSuccessMessageExample;
    /**
     * 响应失败报文示例
     */
    private String responseFailureMessageExample;

    @Data
    public static class ParameterInfo {
        private String serialNumber;
        private String parameterName;
        private String parameterType;
        private String parameterLength;
        private String isRequired;
        private String parameterChName;
        private String desc;
    }
}
