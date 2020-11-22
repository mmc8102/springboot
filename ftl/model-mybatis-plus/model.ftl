package ${basePackageMap['model'].packageName};

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
* ${tableComment!}
* @author ${author!}
* @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
**/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${basePackageMap['model'].className} {

    <#list entityAttrs as ea>
    /**
     * ${ea.comment!}
     */
    private ${ea.javaType} ${ea.fieldName};
    </#list>

}