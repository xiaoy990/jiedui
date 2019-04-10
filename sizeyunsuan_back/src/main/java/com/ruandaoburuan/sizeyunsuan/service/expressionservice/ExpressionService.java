package com.ruandaoburuan.sizeyunsuan.service.expressionservice;

import com.ruandaoburuan.sizeyunsuan.beans.entity.Expression;
import com.ruandaoburuan.sizeyunsuan.beans.factory.ArithmeticFactory;
import com.ruandaoburuan.sizeyunsuan.beans.factory.EquationFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpressionService {
    private ArrayList<String> expList;
    @Resource
    EquationFactory equationFactory;
    @Resource
    ArithmeticFactory arithmeticFactory;
    private void creatExGe(int num){
        equationFactory.setItemNum(num);
        this.expList = equationFactory.creatEquation();
    }

    private  void creatExFraction(int num){
        equationFactory.setItemNum(num);
        this.expList = equationFactory.creatEquationWithFraction();
    }

    public Expression getExObject(int num){
        Expression expression = new Expression();
        do {
            this.creatExGe(num);
            expression.setQuestion(String.join("", this.expList));
            expression.setAnswer(arithmeticFactory.shuntingYard(this.expList));
        }while (ArithmeticFactory.nag);
        return expression;
    }

    public Expression getExObjectWithFraction(int num){
        Expression expression = new Expression();
        do {
            this.creatExFraction(num);
            List temp = new ArrayList();
            for (Object o : expList
            ) {
                temp.add(o.toString());
            }
            expression.setQuestion(String.join("",temp));
            expression.setAnswer(arithmeticFactory.doCacuFc(this.expList));
        }while(ArithmeticFactory.nag);

        return expression;
    }
}
