package com.xiaohua.model.dto.app;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 更新应用请求
 *
 * @author 小花
 * @from 好好学习
 */
@Data
public class AppUpdateRequest implements Serializable {

    /**
     * 使用雪花算法增长id
     */
    private Long id;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 应用描述
     */
    private String appDesc;

    /**
     * 应用图标
     */
    private String appIcon;

    /**
     * 应用类型（0-得分类，1-测评类）
     */
    private Integer appType;

    /**
     * 评分策略（0-自定义，1-AI）
     */
    private Integer scoringStrategy;

    /**
     * 审核状态：0-待审核, 1-通过, 2-拒绝
     */
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    private String reviewMessage;

    /**
     * 审核人 id
     */
    private Long reviewerId;

    /**
     * 审核时间
     */
    private Date reviewTime;



    /**
     * 是否删除 1代表删除，0代表未删除
     */
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}