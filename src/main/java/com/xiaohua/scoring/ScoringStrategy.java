package com.xiaohua.scoring;

import com.xiaohua.model.entity.App;
import com.xiaohua.model.entity.UserAnswer;

import java.util.List;

/**
 * @description: 好好学Java，早日找到好工作
 * @author: XiaoHua
 * 评分策略
 * * *
 **/

public interface ScoringStrategy {
    UserAnswer doScore(List<String> choices, App app) throws Exception;
}
