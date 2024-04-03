package com.example.spring_tboard;

import com.example.spring_tboard.domain.DB.DBController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringTboardApplicationTests {

	@Autowired
	DBController dbController;

	@Test
	void test1() {
		dbController.t1();
	}

}
