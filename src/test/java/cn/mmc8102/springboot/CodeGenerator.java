package cn.mmc8102.springboot;

import cn.mmc8102.mybatisplusgeneratorplus.parser.ConfigParser;
import cn.mmc8102.mybatisplusgeneratorplus.pojo.bo.Config;
import cn.mmc8102.mybatisplusgeneratorplus.pojo.vo.EntityVO;
import cn.mmc8102.mybatisplusgeneratorplus.util.GenerateUtil;
import cn.mmc8102.mybatisplusgeneratorplus.util.InitDb;
import freemarker.template.TemplateException;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

/**
 * 自动生成代码的类
 *
 * @author wangli
 */
public class CodeGenerator {

    public static void main(String[] args) throws DocumentException, IOException, TemplateException {

        //找到配置文件的路径
        String path = System.getProperty("user.dir") + "/src/main/resources/" + "GenerateConfig.xml";
        final Config config = ConfigParser.getConfig(path);
        System.out.println(config);
        //获取得到的表的集合
        List<EntityVO> entityList = InitDb.getInstence(config).initTables();
        for (EntityVO entity : entityList) {
            GenerateUtil.generateTemplate(entity, "model");
            GenerateUtil.generateTemplate(entity, "dao");
            GenerateUtil.generateTemplate(entity, "mapper");
            GenerateUtil.generateTemplate(entity, "service");
            GenerateUtil.generateTemplate(entity, "serviceImpl");
            GenerateUtil.generateTemplate(entity, "controller");
            System.out.println(entity);
        }
        System.out.println("------------生成完毕!-------------");
    }

}
