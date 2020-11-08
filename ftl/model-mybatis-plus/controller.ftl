package ${basePackageMap['controller'].packageName};

import ${basePackageMap['service'].packageName}.${basePackageMap['service'].className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author ${author!}
* @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
**/
@RestController
@RequestMapping("${basePackageMap['model'].className?uncap_first}")
public class ${basePackageMap['controller'].className} {
    @Autowired
    private ${basePackageMap['service'].className} ${basePackageMap['service'].className?substring(1)?uncap_first};

}