package com.ruandaoburuan.sizeyunsuan;

import com.ruandaoburuan.sizeyunsuan.service.expressionservice.ExpressionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SizeyunsuanApplicationTests {
    @Autowired
    ExpressionService expressionService;
    @Test
    public void contextLoads() {
        expressionService.getExObject(5);
    }

}
