package cn.mmc8102.springboot;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author wangli
 * @Date: 2020/10/25 12:53
 */
public class RocketMQTest {

    @Test
    public void testRocketMQProduct() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("hello-producer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setSendMsgTimeout(6000);
        producer.start();
        Message message = new Message("01-hello", "hello-recketmq".getBytes("utf-8"));
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult.getSendStatus());
        producer.shutdown();
    }

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("hello-consumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("01-hello","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : msgs) {
                    System.out.println("消息内容:" + new String(msg.getBody())+",消息id:"+msg.getMsgId());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
