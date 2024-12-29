package com.xiaohua.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohua.model.entity.UserAnswer;
import com.xiaohua.statistic.AppAnswerCountDTO;
import com.xiaohua.statistic.AppAnswerResultCountDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author qq
* @description 针对表【user_answer(用户答题记录)】的数据库操作Mapper
* @createDate 2024-12-13 12:34:11
*/
public interface UserAnswerMapper extends BaseMapper<UserAnswer> {
    @Select("select appId, count(userId) as answerCount from user_answer " +
            "group by appId order by answerCount desc")
    List<AppAnswerCountDTO> doAppAnswerCount();

    @Select("select resultName,count(resultName) as resultCount from user_answer where appId = #{appId} group by resultName order by resultCount desc limit 10")
    List<AppAnswerResultCountDTO> doAppAnswerResultCount(Long appId);

}




