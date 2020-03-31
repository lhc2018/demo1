package com.example.apitest.controller;

import com.example.apitest.entity.CodeEnum;
import com.example.apitest.entity.ResultRsp;
import com.example.apitest.entity.TokenCreateRsp;
import com.example.apitest.entity.monitorUrlRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description: 实时视频监控
 * @author: liying
 * @create: 2020-03-26 16:54:20
 **/
@RestController
@RequestMapping(value = "/vidioMonitor")
public class VedioMonitorController {

    public static Logger logger = LoggerFactory.getLogger(VedioMonitorController.class);

    @Value("${auth.getRealtimeMonitorUrl}")
    private String getRealtimeMonitorUrl;

    @Value("${auth.preUrl}")
    private String preUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取实时监控视频url
     * @param channelId
     * @param subType
     * @param scheme
     * @return
     */
    @GetMapping(value = "/getRealtimeMonitorUrl")
    public ResultRsp getRealtimeMonitorUrl(@RequestParam(value = "channelId",required = true)String channelId,
                                               @RequestParam(value = "subType",required = false) String subType,
                                               @RequestParam(value = "scheme",required = false) String scheme,
                                               @RequestParam(value = "token",required = true) String token){

        ResultRsp resultRsp = null;
        logger.info("获取实时监控视频url参数:channelId:{},subType:{},scheme:{}",channelId,subType,scheme);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Subject-Token",token);
        HttpEntity<ResultRsp> entity = new HttpEntity<>(null,headers);
        Map<String,String> urlVariable = new HashMap<>();
        urlVariable.put("channelId",channelId);
        if(!StringUtils.isEmpty(subType)){
            urlVariable.put("subType",subType);
        }
        if(!StringUtils.isEmpty(scheme)){
            urlVariable.put("scheme",scheme);
        }
        try {
            ResponseEntity<monitorUrlRsp> responseEntity = restTemplate.exchange(preUrl + getRealtimeMonitorUrl, HttpMethod.GET, entity, monitorUrlRsp.class,urlVariable);
            if(responseEntity!=null && responseEntity.getStatusCode().value()== CodeEnum.FIRST_SUCCESS.getCode()){
                monitorUrlRsp body = responseEntity.getBody();
                resultRsp = ResultRsp.success(body);
            }
        } catch (Exception e) {
            logger.error("获取实时监控视频url接口请求异常,{}",e);
            resultRsp = ResultRsp.error();
        }
        return resultRsp;

    }




}
