package com.yyd.yyd.controller;

import com.yyd.yyd.frame.web.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.ResponseMessage;

@RestController
@Slf4j
@RequestMapping("/loggerItem")
public class LoggerItemController {

    @GetMapping("/logInfo")
    public RestResponse logTest(){

        log.debug("=====>测试日志debug级别打印<====");
        log.info("=====>测试日志info级别打印<=====");
        log.error("=====>测试日志error级别打印<====");
        log.warn("=====>测试日志warn级别打印<=====");

        // 使用占位符打印出一些参数信息
        String csdn = "https://blog.csdn.net/qq_27706119";
        String git = "https://github.com/JohnnyHL";
        log.info("======>AndOne丶的CSDN博客：{}；AndOne丶的GitHub地址：{}；", csdn, git);

        return new RestResponse("fasdfasdfasdf");
    }
}
