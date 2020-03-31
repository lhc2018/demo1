package com.example.apitest.controller;

import com.example.apitest.entity.*;
import com.example.apitest.utils.AuthenticationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-26 13:43:12
 **/
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    
    @Value("${auth.preUrl}")
    private String preUrl;

    @Value("${auth.getTokenUrl}")
    private String getTokenUrl;

    @Value("${auth.keepTokenAliveUrl}")
    private String keepTokenAliveUrl;

    public static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 创建会话token api  首次认证
     * @param tokenCreateReq
     * @return
     */
    @PostMapping("/doAuthorizeFirst")
    public ResultRsp doAuthorizeFirst(@Validated @RequestBody TokenCreateReq tokenCreateReq, BindingResult bindingResult){
        ResultRsp resultRsp = null;
        if(bindingResult.hasErrors()){
            logger.info("创建会话接口传入参数数据异常，{}",tokenCreateReq.toString());
            resultRsp = ResultRsp.error();
            return resultRsp;
        }
        logger.info("创建会话token参数:{}",tokenCreateReq.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json;charset=UTF-8");
        HttpEntity<ResultRsp> entity = new HttpEntity<>(null,headers);
        try {
            ResponseEntity<TokenCreateRsp>  responseEntity = restTemplate.exchange(preUrl + getTokenUrl, HttpMethod.POST, entity, TokenCreateRsp.class, tokenCreateReq);
            if(responseEntity!=null && responseEntity.getStatusCode().value()==CodeEnum.FIRST_SUCCESS.getCode()){
                TokenCreateRsp tokenCreateRsp = responseEntity.getBody();
                resultRsp = ResultRsp.success(tokenCreateRsp);
            }
        } catch (Exception e) {
            logger.error("生成会话token接口异常,{}",e);
            resultRsp = ResultRsp.error();
        }
        return resultRsp;
    }

    /**
     * 携带签名 二次认证
     * @return
     */
    @PostMapping(value = "/doAuthorizeSecond")
    public ResultRsp doAuthorizeSecond(@Validated @RequestBody SecondAuthticationReq secondAuthticationReq, BindingResult bindingResult){
        ResultRsp resultRsp = null;
        if(bindingResult.hasErrors()){
            logger.info("认证接口二次请求传入参数数据异常，{}",secondAuthticationReq.toString());
            resultRsp = ResultRsp.error();
            return resultRsp;
        }
        //生成签名
        String username = secondAuthticationReq.getUserName();
        String password = secondAuthticationReq.getPassword();
        String realm = secondAuthticationReq.getRealm();//域信息
        String randomKey = secondAuthticationReq.getRandomKey();//随机密钥
        String encryptType = secondAuthticationReq.getEncryptType();//加密类型 默认MD5
        String signature = AuthenticationUtils.getSignature(username,password,realm,randomKey,encryptType);
        logger.info("生成签名signature：,{}",signature);
        secondAuthticationReq.setSignature(signature);
        //携带签名进行二次认证
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json;charset=UTF-8");
        HttpEntity<SecondAuthticationRsp> entity = new HttpEntity<>(null,headers);
        try {
            ResponseEntity<SecondAuthticationRsp> responseEntity = restTemplate.exchange(preUrl + getTokenUrl, HttpMethod.POST, entity, SecondAuthticationRsp.class, secondAuthticationReq);
            if(responseEntity!=null && responseEntity.getStatusCode().equals(HttpStatus.OK)){
                SecondAuthticationRsp rsp = responseEntity.getBody();
                resultRsp = ResultRsp.success(rsp);
            }
        } catch (RestClientException e) {
            logger.error("认证接口二次请求异常,异常信息{}",e);
            resultRsp = ResultRsp.error();
        }
        return resultRsp;
    }

    /**
     * 会话租期续约
     * @param keepTokenAliveReq
     */
    @PostMapping(value = "/keepTokenAlive")
    public ResultRsp keepTokenAlive(@Validated @RequestBody KeepTokenAliveReq keepTokenAliveReq, BindingResult bindingResult){
        ResultRsp resultRsp = null;
        if(bindingResult.hasErrors()){
            logger.info("会话租期续约接口请求传入参数异常，{}",keepTokenAliveReq.toString());
            resultRsp = ResultRsp.error();
            return resultRsp;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json;charset=UTF-8");
        headers.add("X-Subject-Token",keepTokenAliveReq.getToken());
        HttpEntity<KeepTokenAliveRsp> entity = new HttpEntity<>(null,headers);
        try {
            ResponseEntity<KeepTokenAliveRsp> rsp = restTemplate.exchange(preUrl + keepTokenAliveUrl, HttpMethod.PUT, entity, KeepTokenAliveRsp.class, keepTokenAliveReq);
            if(rsp!=null && rsp.getStatusCode().equals(HttpStatus.OK)){
                resultRsp = rsp.getBody();
            }
        } catch (Exception e) {
            logger.error("会话租期续约接口请求异常,异常信息{}",e);
            resultRsp = ResultRsp.error();
        }
        return resultRsp;
    }

}
