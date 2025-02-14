package com.pwhTest.rocketMqTest;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

public class RocketMQListener  implements MessageListenerConcurrently {
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//      System.out.println("get data from rocketMQ:" + msgs);
      for (MessageExt message : msgs) {

          String msg = new String(message.getBody());
          System.out.println("msg data from rocketMQ:" + msg);
      }

      return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
  }

}
