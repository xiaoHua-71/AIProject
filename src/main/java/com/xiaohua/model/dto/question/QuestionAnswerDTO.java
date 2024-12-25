package com.xiaohua.model.dto.question;

import lombok.Data;

/**
 * 题目答案封装类*
 * @description: 好好学Java
 * @author: XiaoHua
 **/
@Data
public class QuestionAnswerDTO {

    /**
     * 题目
     */
    private String title;

    /**
     * 用户答案
     */
    private String userAnswer;
}
