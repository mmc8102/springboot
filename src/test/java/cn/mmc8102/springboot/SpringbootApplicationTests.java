package cn.mmc8102.springboot;

import cn.mmc8102.springboot.domain.Employee;
import cn.mmc8102.springboot.mapper.SapTemplateValidationMapper;
import cn.mmc8102.springboot.util.JsonTool;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
//@RunWith(SpringRunner.class)
class SpringbootApplicationTests {
    @Autowired
    private SapTemplateValidationMapper sapTemplateValidationMapper;

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

    @Test
    void contextLoads() {
        //String source = "{'biz_type':'si_payment_incomeS','city_code':'330100','sys_service_provider_id':'2088101010464092','si_data':[{'target_idcard_type':'01','request_id':'201902011759182132','target_idcard':'445323199404211217','target_user_name':'张三','target_user_id':'2088102122001010','target_sicard_no':'M40000240','target_extend_params':'{}','mobile':'18100000000','template_id':'XS_si_payment_income_template','template_version':'1.0','template_data':[{'account_balance':'0','account_person':'50','month':'201902','pay_cost_base':'2500','pay_cost_total':'195','pay_cost_person':'195','pay_cost_unit':'1955','extend_params':'{}','si_type':'10'},{'account_balance':'0','account_person':'40','month':'201902','pay_cost_base':'2500','pay_cost_total':'195','pay_cost_person':'195','pay_cost_unit':'1955','extend_params':'{}','si_type':'20'},{'account_balance':'0','account_person':'40','month':'201902','pay_cost_base':'2500','pay_cost_total':'195','pay_cost_person':'195','pay_cost_unit':'1955','extend_params':'{}','si_type':'30'},{'account_balance':'0','account_person':'40','month':'201902','pay_cost_base':'2500','pay_cost_total':'195','pay_cost_person':'195','pay_cost_unit':'1955','extend_params':'{}','si_type':'40'}],'service_return_url':'https://www.alipay.com'}],'extend_params':'{}','target_notify_time':'20181226151421'}";
        //String template = "{'si_type':{'notNull':'true','length':'32','toFiled':'siType'},'account_balance':{'notNull':'false','length':'15','toFiled':'accountBalance'},'pay_cost_base':{'notNull':'false','length':'15','toFiled':'payCostBase'},'pay_cost_month':{'notNull':'true','length':'6','toFiled':'payCostMonth'},'pay_cost_total':{'notNull':'false','length':'15','toFiled':'payCostTotal'},'pay_cost_person':{'notNull':'false','length':'15','toFiled':'payCostPerson'},'pay_cost_unit':{'notNull':'false','length':'15','toFiled':'payCostUnit'},'account_person':{'notNull':'false','length':'15','toFiled':'accountPerson'},'extend_params_test1':{'notNull':'false','length':'15','toFiled':'extend_params'},'extend_params_test2':{'notNull':'false','length':'15','toFiled':'extend_params'}}";
        String sap = sapTemplateValidationMapper.getByTemplateId(1);
        System.out.println(sap);
    }

    @Test
    public void testJsonUtils(){
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("code",200);
        map1.put("msg","success");
        map2.put("username","mmc");
        map2.put("password","admin");
        map.put("header",map1);
        map.put("body",map2);

        Employee e1 = new Employee();
        e1.setUsername("mmc");
        e1.setPassword("admin1");
        Employee e2 = new Employee();
        e2.setUsername("hhh");
        e2.setPassword("admin2");
        List<Employee> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);

        String json = JsonTool.toJson(list);
        String mapJson = JsonTool.toJson(map);
        Employee employee = JsonTool.toObject(mapJson, Employee.class);
        Map map3 = JsonTool.toObject(mapJson, Map.class);
        List<Employee> employeeList = JsonTool.toList(json);
        System.out.println();
    }
}
