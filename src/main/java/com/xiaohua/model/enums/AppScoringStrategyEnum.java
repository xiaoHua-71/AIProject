package com.xiaohua.model.enums;

import cn.hutool.core.util.ObjectUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 好好学Java，早日找到好工作
 * @author: XiaoHua
 *
 * 增加枚举类* *
 **/

public enum AppScoringStrategyEnum {

    CUSTOM("自定义",0),
    AI("AI",1);

    private final String text;

    private final int value;


    AppScoringStrategyEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据value获取枚举*
     */
    public static AppScoringStrategyEnum getEnumByValue(Integer value){
        if(ObjectUtil.isEmpty(value)){
            return null;
        }
        for (AppScoringStrategyEnum anEnum : AppScoringStrategyEnum.values()){
            if(anEnum.value == value){
                return anEnum;
            }
        }
        return null;

    }

    /**
     * 获取值列表*
     */
    public static List<Integer> getValues(){
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public int getValue(){
        return value;
    }

    public String getText(){
        return text;

    }

    public static void main(String[] args) {
        List<Integer> values = AppScoringStrategyEnum.getValues();
        System.out.println(values);
    }




}
