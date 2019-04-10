package com.ruandaoburuan.sizeyunsuan.beans.factory;

import com.ruandaoburuan.sizeyunsuan.beans.entity.Fraction;
import com.ruandaoburuan.sizeyunsuan.beans.entity.Operator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;
@Component
public class EquationFactory{
    ArrayList<String> list = new ArrayList<>();
    Random random = new Random();
    int itemNum;
    int[] num ;
    //计数器 记录左右括号差
    int count = 0;

    public EquationFactory(int itemNum){
        this.itemNum = itemNum;
        num = new int[this.itemNum];
    }

    public EquationFactory(){
        this.itemNum = 5;
        num = new int[this.itemNum];
    }

    public ArrayList creatEquation(){
        int position = -1;
        boolean flag;
        for(int i = 0;i < num.length; i++){
            flag = true;
            int deviation = 0;
            if(i<num.length-1){
                num[i] = random.nextInt(100)+1;
                if(i>0&&!list.get(position).equals("÷")&&random.nextInt(4)==1){
                    list.add("(");
                    position++;
                    count++;
                    flag = false;
                }
                list.add(String.valueOf(num[i]));
                position++;
                if(count!=0&&random.nextInt(4)==1&&flag){
                    list.add(")");
                    deviation++;
                    count--;
                }
                Operator operator = new Operator(random.nextInt(3));
                if(i>0&&list.get(position-1).equals("÷")&&operator.getValue().equals("÷")) {
                    list.add("×");
                    position++;
                    if(random.nextInt(4)==1&&flag){
                        list.add("(");
                        position++;
                        count++;
                    }
                }else {
                    list.add(operator.getValue());
                    position++;
                }
            }
            //补全所有括号对
            else {
                num[i] = random.nextInt(100)+1;
                list.add(String.valueOf(num[i]));
                if(list.get(position).equals("(")){
                    list.remove(position);
                    count--;
                }
                while(count!=0){
                    list.add(")");
                    count--;
                }
            }
        }
        //避免出现小数
        int tempCount = 0;
        for(String str:list){
            tempCount++;
        }
        boolean flag1;
        for(int i=0;i < tempCount;i++){
            flag1 = true;
            int a = 0,c = 0;
            String b = null;
            try{
                a = Integer.valueOf(list.get(i));
                b = list.get(i+1);
                c = Integer.valueOf(list.get(i + 2));
            }
            catch (Exception e){
                flag1 = false;
            }
            if(flag1&&b.equals("÷")&&c!=0){
                int temp = a%c;
                if(temp!=0){
                    list.set( i,String.valueOf(a + c - temp));
                }
            }
        }
        return list;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
        num = new int[itemNum];
    }

    //创建分数表达式
    public ArrayList creatEquationWithFraction() {
        ArrayList<Object> list1 = new ArrayList<>();
        while(itemNum!=0) {
            int numerator = random.nextInt(5)+1;
            int denomination = numerator + random.nextInt(10)+1;
            Fraction fraction = new Fraction(numerator, denomination);
            list1.add(fraction);
            if(itemNum-1!=0) {
                Operator operator = new Operator(1);
                list1.add(operator.getValue());
            }
            itemNum--;
        }
        return list1;
    }
}
