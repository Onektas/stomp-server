package top.onektas.stompserver.model;

import lombok.Data;

/**
 * 消息实体类
 *
 * @onektas
 */
@Data
public class MessageBody {
    /* 消息内容 */
    private String content;
    /* 广播转发的目标地址（告知 STOMP 代理转发到哪个地方） */
    private String destination;
}
