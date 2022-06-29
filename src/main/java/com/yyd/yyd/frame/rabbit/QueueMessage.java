package com.yyd.yyd.frame.rabbit;



import com.yyd.yyd.frame.trace.TraceMdcUtil;
import com.yyd.yyd.utils.JsonUtils;
import com.yyd.yyd.utils.Utility;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;

import java.nio.charset.StandardCharsets;


@Data
public class QueueMessage {
    /**
     * 消息类型
     */
    private MessageType messageType;
    /**
     * 消息任务号
     */
    private String messageGid;
    /**
     * 消息明细
     */
    private String detail;

    /**
     * 日志ID
     */
    private String traceId;

    private long inTimeMillis;
    private long outTimeMillis;

    public QueueMessage() {
    }

    public QueueMessage(MessageType messageType, String detail) {
        this.messageType = messageType;
        this.messageGid = Utility.simpleUUID();
        this.detail = detail;

        this.inTimeMillis = System.currentTimeMillis();
        this.traceId = TraceMdcUtil.getTraceId();
    }

    public Message toAmqpMessage() {
        return MessageBuilder.withBody(JsonUtils.toJson(this).getBytes(StandardCharsets.UTF_8)).build();
    }

    public static QueueMessage fromAmqpMessage(Message message) {
        String json = StringUtils.toEncodedString(message.getBody(), StandardCharsets.UTF_8);
        QueueMessage msg = JsonUtils.fromJson(json, QueueMessage.class);
        msg.setOutTimeMillis(System.currentTimeMillis());

        TraceMdcUtil.setTraceId(msg.getTraceId());

        return msg;
    }


}
