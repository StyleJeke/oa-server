package com.jxwgroup.oaserver.timeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
@Slf4j
public class Timmer {

    @Scheduled(fixedDelay = 1000)
    public void timeTaskDemo(){

    }

}
