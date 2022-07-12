package com.zzy.eban.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.zzy.eban.pojo.Employee;
import com.zzy.eban.pojo.MailConstants;
import com.zzy.eban.pojo.MailLog;
import com.zzy.eban.service.IEmployeeService;
import com.zzy.eban.service.IMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 邮件发送定时任务
 *
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/3/26
 */
@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /*邮件发送定时任务10s
    发送一次*/

    @Scheduled(cron = "0/10 * * * * ?")
    public void mialTask() {
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>().eq("status", 0).
                lt("tryTime", LocalDateTime.now()));
        list.forEach(mailLog -> {
            //如果重试次数大于3次，更新投递状态为失败不再尝试
            if (3 <= mailLog.getCount()) {
                mailLogService.update(new UpdateWrapper<MailLog>().set("status", 2).eq("msgId", mailLog.getMsgId()));
            }
            mailLogService.update(new UpdateWrapper<MailLog>().set("count", mailLog.getCount() + 1).set("updateTime",
                    LocalDateTime.now()).set("tryTime", LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT)).
                    eq("msgId", mailLog.getMsgId()));
            Employee employee = employeeService.getEmployee(mailLog.getEid()).get(0);

            //重新发送消息
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME,
                    employee, new CorrelationData(mailLog.getMsgId()));


        });
    }
}
