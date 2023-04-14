package com.busstation.config;


import com.busstation.services.TokenService;
import com.busstation.utils.JwtProviderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SchedulerConfig {

    @Autowired
    private TokenService tokenService;

//    @Scheduled(fixedDelay =300000)
//    public void cancelUnpaidChair(){
//        System.out.println("after 5 minutes if not reserved seats will be restored");
//    }

    //Will test later
    @Scheduled(fixedDelay =3600000)
    public void  checkTokenExpired(){
        tokenService.heckTokenExpired();
    }
}
