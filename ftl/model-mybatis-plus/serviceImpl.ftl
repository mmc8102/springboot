package ${basePackageMap['serviceImpl'].packageName};

import ${basePackageMap['dao'].packageName}.${basePackageMap['dao'].className};
import ${basePackageMap['service'].packageName}.${basePackageMap['service'].className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author ${author!}
* @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
**/
@Service
public class ${basePackageMap['serviceImpl'].className} implements ${basePackageMap['service'].className}{
    @Autowired
    private ${basePackageMap['dao'].className} ${basePackageMap['dao'].className?uncap_first};

}