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

public enum AppTypeEnum {

    SCORE("得分类",0),
    TEST("测评类",1);

    private final String text;

    private final int value;


    AppTypeEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据value获取枚举*
     */
    public static AppTypeEnum getEnumByValue(Integer value){
        if(ObjectUtil.isEmpty(value)){
            return null;
        }
        for (AppTypeEnum anEnum : AppTypeEnum.values()){
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
        List<Integer> values = AppTypeEnum.getValues();
        System.out.println(values);
    }




}
