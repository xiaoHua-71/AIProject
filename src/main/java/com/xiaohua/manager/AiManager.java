package com.xiaohua.manager;

import com.xiaohua.common.ErrorCode;
import com.xiaohua.exception.BusinessException;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.ChatCompletionRequest;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import com.zhipu.oapi.service.v4.model.ChatMessageRole;
import com.zhipu.oapi.service.v4.model.ModelApiResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 好好学Java
 * @author: XiaoHua
 **/
@Component
public class AiManager {
    @Resource
    private ClientV4 clientV4;

    //稳定随机数，生成的回答会与发送的问题更精准的匹配
    private static final float STABLE_TEMPERATURE = 0.05f;
    //不稳定随机数，生成的回答会与发送的问题不是很匹配，回答的天马行空
    private static final float UNSTABLE_TEMPERATURE = 0.99f;


    /**
     * * 同步答案不稳定请求
     * @param systemMessage
     * @param userMessage
     * @return
     */
    public String doSyncUnstableRequest(String systemMessage,String userMessage) {
        return doRequest(systemMessage,userMessage,Boolean.FALSE,UNSTABLE_TEMPERATURE);
    }
    /**
     * * 同步答案稳定请求
     * @param systemMessage
     * @param userMessage
     * @return
     */
    public String doSyncStableRequest(String systemMessage,String userMessage) {
        return doRequest(systemMessage,userMessage,Boolean.FALSE,STABLE_TEMPERATURE);
    }

    /**
     * * 通用同步请求
     * @param systemMessage
     * @param userMessage
     * @param temperature
     * @return
     */
    public String doSyncRequest(String systemMessage,String userMessage, Float temperature) {
        return doRequest(systemMessage,userMessage,Boolean.FALSE,temperature);
    }

    /**
     * 通用请求，简化消息传递*
     * @param systemMessage
     * @param userMessage
     * @param stream
     * @param temperature
     * @return
     */
    public String doRequest(String systemMessage, String userMessage, Boolean stream, Float temperature) {
        // 构造请求
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemChatMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), systemMessage);
        ChatMessage userChatMessage = new ChatMessage(ChatMessageRole.USER.value(), userMessage);
        messages.add(systemChatMessage);
        messages.add(userChatMessage);
        return doRequest(messages, stream, temperature);
    }


    /**
     *通用请求
     * @param messages
     * @param stream
     * @param temperature
     * @return
     */

    public String doRequest(List<ChatMessage> messages, Boolean stream, Float temperature) {
        // 构造请求
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(stream)
                .invokeMethod(Constants.invokeMethod)
                .temperature(temperature)
                .messages(messages)
                .build();
        try {
            ModelApiResponse invokeModelApiResp = clientV4.invokeModelApi(chatCompletionRequest);
            return invokeModelApiResp.getData().getChoices().get(0).toString();
        }catch (Exception e){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,e.getMessage());
        }

    }

}
