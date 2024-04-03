package com.example.spring_tboard.domain.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBController {

    @Autowired
    MyDB memoryDB;
    public void t1 () {
        memoryDB.run();
    }
}
