package com.zzy.eban.pojo;

/**
 * 消息的状态
 *
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/3/26
 */
public class MailConstants {
    //消息投递中状态
    public static final Integer DELIVERING = 0;

    //消息投递成功
    public static final Integer   SUCCESS = 1;

    //消息投递失败
    public static final Integer FAILURE = 2;

    //消息最大尝试次数
    public static final Integer MAX_TRY_COUNT = 3;

    //消息超时时间
    public static final Integer    MSG_TIMEOUT = 1;

    //消息队列
    public static final String MAIL_QUEUE_NAME = "mail.queue";

    //消息交换机
    public  static final String MAIL_EXCHANGE_NAME = "mail.exchange";

    //路由
    public static final String MAIL_ROUTING_KEY_NAME = "mail.routing.key";

}
