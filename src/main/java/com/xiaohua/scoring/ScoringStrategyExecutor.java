package com.xiaohua.scoring;

import com.xiaohua.common.ErrorCode;
import com.xiaohua.exception.BusinessException;
import com.xiaohua.model.entity.App;
import com.xiaohua.model.entity.UserAnswer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 好好学Java
 * @author: XiaoHua
 **/
@Service
public class ScoringStrategyExecutor {

    @Resource
    private List<ScoringStrategy> scoringStrategyList;

    /**
     * 评分
     *
     * @param choiceList
     * @param app
     * @return
     * @throws Exception
     */

    public UserAnswer doScore(List<String> choiceList, App app) throws Exception{
        Integer appType = app.getAppType();
        Integer appScoringStrategy = app.getScoringStrategy();
        if(appType == null || appScoringStrategy == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"应用配置有误，未找到匹配得策略");
        }
        for (ScoringStrategy strategy : scoringStrategyList){
            if(strategy.getClass().isAnnotationPresent(ScoringStrategyConfig.class)){
                ScoringStrategyConfig scoringStrategyConfig = strategy.getClass().getAnnotation(ScoringStrategyConfig.class);
                if(scoringStrategyConfig.appType() == appType && scoringStrategyConfig.scoringStrategy() == appScoringStrategy){
                    return strategy.doScore(choiceList,app);
                }
            }
        }
        throw new BusinessException(ErrorCode.SYSTEM_ERROR,"应用配置有误，未找到匹配的策略");

    }
}
