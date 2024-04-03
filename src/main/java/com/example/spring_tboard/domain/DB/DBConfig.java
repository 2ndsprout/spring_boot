package com.example.spring_tboard.domain.DB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정 용
@Configuration
public class DBConfig {


    // 객체 리턴 메서드
    // 메서드가 리턴하는 객체를 bean 등록

    @Bean
    public  MyDB myDB () {
        return new MemoryDB(); // 이부분만 수정하면 컨트롤러는 수정하지 않아도 됨.
    }


}
