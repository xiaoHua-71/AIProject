package com.xiaohua.statistic;

import lombok.Data;

/**
 * @description: 好好学Java
 * @author: XiaoHua
 **/
@Data
public class AppAnswerCountDTO {

    /**
     * appId *
     */
    private Long appId;

    /**
     * 用户提交答案数*
     */
    private Long answerCount;
}
