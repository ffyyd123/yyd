package com.yyd.yyd.frame.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RabbitProducer {

//    @Value("${rabbit.exchange}")
//    private String exchange;
//    @Value("${rabbit.key.default}")
//    private String defaultKey;
//
//    @Value("${rabbit.exchange.delay}")
//    private String delayExchange;
//    @Value("${rabbit.key.delay}")
//    private String delayKey;

//    @Resource
//    private RabbitTemplate rabbitTemplate;
//
//
//    public void sendMessage(MessageType messageType, String detail) {
//        try {
//            QueueMessage message = new QueueMessage(messageType, detail);
//            rabbitTemplate.send(exchange, defaultKey, message.toAmqpMessage());
//        } catch (Exception ex) {
//            log.error("sendMessage | entry | {} | {}", messageType, detail, ex);
//        }
//    }
//
//    public void sendDelayMessage(MessageType messageType, String detail, int delaySeconds) {
//        log.info("sendDelayMessage | entry | {} | {} | {}", messageType, detail, delaySeconds);
//        try {
//            QueueMessage message = new QueueMessage(messageType, detail);
//            rabbitTemplate.convertAndSend(delayExchange, delayKey, message.toAmqpMessage(), new MessagePostProcessor() {
//                @Override
//                public Message postProcessMessage(Message message) throws AmqpException {
//                    message.getMessageProperties().setHeader("x-delay", delaySeconds * 1000);
//                    return message;
//                }
//            });
//
//        } catch (Exception ex) {
//            log.error("sendDelayMessage | entry | {} | {} | {}", messageType, detail, delaySeconds, ex);
//        }
//    }
}
