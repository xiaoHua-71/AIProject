package com.xiaohua.model.dto.question;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 好好学Java
 * @author: XiaoHua
 **/
@Data
public class AiGenerateQuestionRequest implements Serializable {

    /**
     *应用id*
     */
    private Long appId;

    /**
     *题目数*
     */
    int questionNumber = 10;

    /**
     * 选项数*
     */
    int optionNumber = 2;

    private static final long serialVersionUID = 1L;


}
