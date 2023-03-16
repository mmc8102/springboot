package cn.mmc8102.springboot;

import cn.mmc8102.springboot.domain.Employee;
import cn.mmc8102.springboot.mapper.SapTemplateValidationMapper;
import cn.mmc8102.springboot.utils.JsonTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    void contextLoads() {
        Map<String, String> map = new HashMap<>();
        map.put("testKey", "testKey");
        System.out.println(JsonTool.toJson(map));
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
