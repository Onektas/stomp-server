package top.onektas.stompserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import top.onektas.stompserver.model.MessageBody;

/**
 * 消息 Controller 类
 *
 * @onektas
 */
@Controller
public class MessageController {

    /**
     * 消息发送工具对象
     */
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * 接受客户端消息
     * <p>
     * 广播发送消息，将消息发送到指定的目标地址
     */
    @MessageMapping("/test")
    public void sendTopicMessage(MessageBody messageBody) {
        // 接受客户端消息并传入MysqlController类进行处理
        String result = new MysqlController().mysqlConntroller(messageBody.getContent());
        System.out.println(result);
        messageBody.setContent(result);

        // 将消息发送到 WebSocket 配置类中配置的代理中（/topic）进行消息转发
        simpMessageSendingOperations.convertAndSend(messageBody.getDestination(), messageBody);
    }

}
