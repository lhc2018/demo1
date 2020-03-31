package com.example.demo;

import com.example.apitest.controller.AuthenticationController;
import com.example.apitest.controller.VedioMonitorController;
import com.example.apitest.entity.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

    public static Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private VedioMonitorController vedioMonitorController;

    @Autowired
    private BindingResult result;

    //第一次认证
    @Test
    void test1() {
        TokenCreateReq req = new TokenCreateReq();
        req.setUserName("xxxx");
        req.setClientType("winpc");
//        req.setIpAddress("");
        ResultRsp resultRsp = authenticationController.doAuthorizeFirst(req, result);
        TokenCreateRsp tokenCreateRsp = (TokenCreateRsp)resultRsp.getT();
        logger.info("####第一次认证#####：tokenCreateRsp:{},code:{},message:{}",tokenCreateRsp.toString(),resultRsp.getCode(),resultRsp.getMessage());
    }


    //第二次认证
    @Test
    void test2() {
        SecondAuthticationReq req = new SecondAuthticationReq();
        req.setSignature("");
        req.setClientType("winpc");
        req.setEncryptType("MD5");
        req.setPassword("");
        req.setRandomKey("");
        req.setRealm("");
        req.setUserName("");
//        req.setIpAddress("");
        ResultRsp resultRsp = authenticationController.doAuthorizeSecond(req, result);
        SecondAuthticationRsp secondAuthticationRsp = (SecondAuthticationRsp)resultRsp.getT();
        logger.info("####第二次认证#####：secondAuthticationRsp:{},code:{},message:{}",secondAuthticationRsp.toString(),resultRsp.getCode(),resultRsp.getMessage());
    }

    //保活
    @Test
    void test3() {
        KeepTokenAliveReq req = new KeepTokenAliveReq();
        req.setToken("");
        ResultRsp resultRsp = authenticationController.keepTokenAlive(req, result);
        KeepTokenAliveRsp keepTokenAliveRsp = (KeepTokenAliveRsp)resultRsp.getT();
        logger.info("####保活接口#####：code:{},message:{}",keepTokenAliveRsp.getCode(),keepTokenAliveRsp.getMessage());
    }


    //获取视频播放地址
    @Test
    void test4() {
        String channelId = "";
        String token = "";
        ResultRsp resultRsp = vedioMonitorController.getRealtimeMonitorUrl(channelId, null,null,token);
        monitorUrlRsp monitorUrlRsp = (monitorUrlRsp)resultRsp.getT();
        logger.info("####保活接口#####：monitorUrlRsp:{},code:{},message:{}",monitorUrlRsp.toString(),resultRsp.getCode(),resultRsp.getMessage());
    }



}
