package cn.mmc8102.springboot.query;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @Author: wangli28
 * @Date: 2020/7/20 10:43
 * 分页查询对象用于HIUI
 */
@Data
public class PageResult2<T> {
    private Page pageInfo;
    private List<T> data;

    public PageResult2(int count, List data, int page, int pageSize){
        this.data = data;
        this.pageInfo = new Page(count, page, pageSize);
    }

    public static PageResult empty(int page, int pageSize){
        return new PageResult(0, Collections.EMPTY_LIST, page, pageSize);
    }

    @Data
    public static class Page {
        private int total;
        private int page;
        private int pageSize;
        private Integer totalPage;

        public Page(Integer total, Integer page, Integer pageSize) {
            this.page = page;
            this.pageSize = pageSize;
            this.total = total;
            this.totalPage = this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1;
        }
    }
}
