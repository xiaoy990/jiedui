package com.ruandaoburuan.sizeyunsuan.controller.expressioncontroller;

import com.ruandaoburuan.sizeyunsuan.beans.entity.Expression;
import com.ruandaoburuan.sizeyunsuan.service.expressionservice.ExpressionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/exp")
public class ExpressionController {
    @Resource
    ExpressionService expressionService;
    @RequestMapping("/creatge")
    public List creatExpressions(int expNum,int itemNum){
        List<Expression> exResult = new ArrayList<>();
        for (int i = 0; i < expNum; i++) {
            Expression expression = expressionService.getExObject(itemNum);
            expression.setId(i);
            exResult.add(expression);
        }
        return exResult;
    }
    @RequestMapping("/creatfr")
    public List creatExpWithFraction(int expNum,int itemNum){
        List<Expression> exResult = new ArrayList<>();
        for (int i = 0; i < expNum; i++) {
            Expression expression = expressionService.getExObjectWithFraction(itemNum);
            expression.setId(i);
            exResult.add(expression);
        }
        return exResult;
    }
}
