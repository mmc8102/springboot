package cn.mmc8102.springboot.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 所有高级查询对象的共同代码封装
 * @author 16282
 */
@Getter
@Setter
public class QueryObject{
	/*private Integer currentPage = 1;
	private Integer pageSize = 10;*/
	private Integer page = 1;
	private Integer rows = 10;

	public int getStart() {
		if (page == null) {
			page = 1;
		}
		return (page - 1) * rows;
	}
}
