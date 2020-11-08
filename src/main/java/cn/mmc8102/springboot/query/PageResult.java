package cn.mmc8102.springboot.query;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 16282
 * 分页查询结果对象
 */
@Getter
@Setter
public class PageResult {
	/**
	 * easyui使用
	 */
	private Integer total;
	private List<?> rows;

	/**
	 * 当前页
	 */
	private Integer currentPage = 1;
	/**
	 * 每页条数
	 */
	private Integer pageSize = 10;
	/**
	 * 上一页
	 */
	private Integer prevPage;
	/**
	 * 下一页
	 */
	private Integer nextPage;
	/**
	 * 总页数
	 */
	private Integer totalPage;

	public PageResult(int total, List<?> rows, Integer currentPage, Integer pageSize) {
		this.total = total;
		this.rows = rows;
		this.currentPage = currentPage == null? 1 : currentPage;
		this.pageSize = pageSize;
		// ----------------------------------------
		this.totalPage = this.total % this.pageSize == 0 ? this.total
				/ this.pageSize : this.total / this.pageSize + 1;

		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1
				: this.totalPage;
	}

	/**
	 * 如果总数据条数为0,返回一个空集
	 */
	public static PageResult empty(Integer pageSize) {
		return new PageResult(0,new ArrayList<>(),  1, pageSize);
	}
	/**
	 * 当前页
	 *//*
	private Integer currentPage = 1;
	*//**
	 * 每页条数
	 *//*
	private Integer pageSize = 10;



	*//**
	 * 当前页的结果集数据
	 *//*
	private List listData;
	*//**
	 * 总数据条数
	 *//*
	private Integer totalCount;

	*//**
	 * 上一页
	 *//*
	private Integer prevPage;
	*//**
	 * 下一页
	 *//*
	private Integer nextPage;
	*//**
	 * 总页数
	 *//*
	private Integer totalPage;

	public int getTotalPage() {
		return totalPage==0?1:totalPage;
	}

	*//**
	 * 如果总数据条数为0,返回一个空集
 	 *//*
	public static PageResult empty(Integer pageSize) {
		return new PageResult(new ArrayList<>(), 0, 1, pageSize);
	}

	public PageResult(List listData, Integer totalCount, Integer currentPage,
			Integer pageSize) {
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage == null? 1 : currentPage;
		this.pageSize = pageSize;
		// ----------------------------------------
		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount
				/ this.pageSize : this.totalCount / this.pageSize + 1;

		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1
				: this.totalPage;
	}*/


}
