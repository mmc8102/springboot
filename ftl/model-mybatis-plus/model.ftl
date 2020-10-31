package ${basePackageMap['model'].packageName};

import lombok.Data;

/**
* ${tableComment!}
* @author ${author!}
* @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
**/
@Data
public class ${basePackageMap['model'].className} {

    <#list entityAttrs as ea>
    /**
     * ${ea.comment!}
     */
    private ${ea.javaType} ${ea.fieldName};
    </#list>

}