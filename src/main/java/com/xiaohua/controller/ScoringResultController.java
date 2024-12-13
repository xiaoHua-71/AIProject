package com.xiaohua.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaohua.annotation.AuthCheck;
import com.xiaohua.common.BaseResponse;
import com.xiaohua.common.DeleteRequest;
import com.xiaohua.common.ErrorCode;
import com.xiaohua.common.ResultUtils;
import com.xiaohua.constant.UserConstant;
import com.xiaohua.exception.BusinessException;
import com.xiaohua.exception.ThrowUtils;
import com.xiaohua.model.dto.scoringresult.ScoringResultAddRequest;
import com.xiaohua.model.dto.scoringresult.ScoringResultEditRequest;
import com.xiaohua.model.dto.scoringresult.ScoringResultQueryRequest;
import com.xiaohua.model.dto.scoringresult.ScoringResultUpdateRequest;
import com.xiaohua.model.entity.ScoringResult;
import com.xiaohua.model.entity.User;
import com.xiaohua.model.vo.ScoringResultVO;
import com.xiaohua.service.ScoringResultService;
import com.xiaohua.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 评分结果接口
 *
 * @author 小花
 * @from 好好学习
 */
@RestController
@RequestMapping("/scoringresult")
@Slf4j
public class ScoringResultController {

    @Resource
    private ScoringResultService scoringresultService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建评分结果
     *
     * @param scoringresultAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addScoringResult(@RequestBody ScoringResultAddRequest scoringresultAddRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(scoringresultAddRequest == null, ErrorCode.PARAMS_ERROR);
        // todo 在此处将实体类和 DTO 进行转换
        ScoringResult scoringresult = new ScoringResult();
        BeanUtils.copyProperties(scoringresultAddRequest, scoringresult);
        // 数据校验
        scoringresultService.validScoringResult(scoringresult, true);
        // todo 填充默认值
        User loginUser = userService.getLoginUser(request);
        scoringresult.setUserId(loginUser.getId());
        // 写入数据库
        boolean result = scoringresultService.save(scoringresult);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        // 返回新写入的数据 id
        long newScoringResultId = scoringresult.getId();
        return ResultUtils.success(newScoringResultId);
    }

    /**
     * 删除评分结果
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteScoringResult(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        ScoringResult oldScoringResult = scoringresultService.getById(id);
        ThrowUtils.throwIf(oldScoringResult == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldScoringResult.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = scoringresultService.removeById(id);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 更新评分结果（仅管理员可用）
     *
     * @param scoringresultUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateScoringResult(@RequestBody ScoringResultUpdateRequest scoringresultUpdateRequest) {
        if (scoringresultUpdateRequest == null || scoringresultUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        ScoringResult scoringresult = new ScoringResult();
        BeanUtils.copyProperties(scoringresultUpdateRequest, scoringresult);
        // 数据校验
        scoringresultService.validScoringResult(scoringresult, false);
        // 判断是否存在
        long id = scoringresultUpdateRequest.getId();
        ScoringResult oldScoringResult = scoringresultService.getById(id);
        ThrowUtils.throwIf(oldScoringResult == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = scoringresultService.updateById(scoringresult);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 根据 id 获取评分结果（封装类）
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<ScoringResultVO> getScoringResultVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        ScoringResult scoringresult = scoringresultService.getById(id);
        ThrowUtils.throwIf(scoringresult == null, ErrorCode.NOT_FOUND_ERROR);
        // 获取封装类
        return ResultUtils.success(scoringresultService.getScoringResultVO(scoringresult, request));
    }

    /**
     * 分页获取评分结果列表（仅管理员可用）
     *
     * @param scoringresultQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<ScoringResult>> listScoringResultByPage(@RequestBody ScoringResultQueryRequest scoringresultQueryRequest) {
        long current = scoringresultQueryRequest.getCurrent();
        long size = scoringresultQueryRequest.getPageSize();
        // 查询数据库
        Page<ScoringResult> scoringresultPage = scoringresultService.page(new Page<>(current, size),
                scoringresultService.getQueryWrapper(scoringresultQueryRequest));
        return ResultUtils.success(scoringresultPage);
    }

    /**
     * 分页获取评分结果列表（封装类）
     *
     * @param scoringresultQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<ScoringResultVO>> listScoringResultVOByPage(@RequestBody ScoringResultQueryRequest scoringresultQueryRequest,
                                                               HttpServletRequest request) {
        long current = scoringresultQueryRequest.getCurrent();
        long size = scoringresultQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        Page<ScoringResult> scoringresultPage = scoringresultService.page(new Page<>(current, size),
                scoringresultService.getQueryWrapper(scoringresultQueryRequest));
        // 获取封装类
        return ResultUtils.success(scoringresultService.getScoringResultVOPage(scoringresultPage, request));
    }

    /**
     * 分页获取当前登录用户创建的评分结果列表
     *
     * @param scoringresultQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResponse<Page<ScoringResultVO>> listMyScoringResultVOByPage(@RequestBody ScoringResultQueryRequest scoringresultQueryRequest,
                                                                 HttpServletRequest request) {
        ThrowUtils.throwIf(scoringresultQueryRequest == null, ErrorCode.PARAMS_ERROR);
        // 补充查询条件，只查询当前登录用户的数据
        User loginUser = userService.getLoginUser(request);
        scoringresultQueryRequest.setUserId(loginUser.getId());
        long current = scoringresultQueryRequest.getCurrent();
        long size = scoringresultQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        Page<ScoringResult> scoringresultPage = scoringresultService.page(new Page<>(current, size),
                scoringresultService.getQueryWrapper(scoringresultQueryRequest));
        // 获取封装类
        return ResultUtils.success(scoringresultService.getScoringResultVOPage(scoringresultPage, request));
    }

    /**
     * 编辑评分结果（给用户使用）
     *
     * @param scoringresultEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editScoringResult(@RequestBody ScoringResultEditRequest scoringresultEditRequest, HttpServletRequest request) {
        if (scoringresultEditRequest == null || scoringresultEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        ScoringResult scoringresult = new ScoringResult();
        BeanUtils.copyProperties(scoringresultEditRequest, scoringresult);
        // 数据校验
        scoringresultService.validScoringResult(scoringresult, false);
        User loginUser = userService.getLoginUser(request);
        // 判断是否存在
        long id = scoringresultEditRequest.getId();
        ScoringResult oldScoringResult = scoringresultService.getById(id);
        ThrowUtils.throwIf(oldScoringResult == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        if (!oldScoringResult.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = scoringresultService.updateById(scoringresult);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    // endregion
}
