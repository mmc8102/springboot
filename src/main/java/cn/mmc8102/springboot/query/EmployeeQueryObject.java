package cn.mmc8102.springboot.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 课程高级查询对象
 * @author 16282
 *
 */
@Getter
@Setter
public class EmployeeQueryObject extends QueryObject {
	private Long id;
	/**
	 * 博客状态
	 */
	private Integer status = 0;
	/**
	 * 关键字查询
	 */
	private String keyWord;

	/**
	 * 博客类型
	 */
	private Long typeId;
	
	public String getKeyWord() {
		//return StringUtils.hasLength(keyWord)?keyWord.trim():null;
		return StringUtils.isNotBlank(keyWord)?keyWord.trim():null;
	}
}
