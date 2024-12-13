package com.xiaohua.model.enums;

import cn.hutool.core.util.ObjectUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 好好学Java，早日找到好工作
 * @author: XiaoHua
 * @create: 2024-12-13 14:21
 **/

public enum ReviewStatusEnum {

    REVIEWING("待审核",0),
    PASS("通过",1),
    REJECT("拒绝",2);

    private final String text;

    private final int value;


    ReviewStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据value获取枚举*
     */
    public static ReviewStatusEnum getEnumByValue(Integer value){
        if(ObjectUtil.isEmpty(value)){
            return null;
        }
        for (ReviewStatusEnum anEnum : ReviewStatusEnum.values()){
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
        List<Integer> values = ReviewStatusEnum.getValues();
        System.out.println(values);
    }




}
