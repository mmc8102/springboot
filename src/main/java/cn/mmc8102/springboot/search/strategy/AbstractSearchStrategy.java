package cn.mmc8102.springboot.search.strategy;

import cn.mmc8102.springboot.search.common.Result;
import cn.mmc8102.springboot.search.common.ResultCode;
import cn.mmc8102.springboot.search.request.ItemQuery;
import cn.mmc8102.springboot.search.request.ItemQueryRequest;

/**
 * @author wangli
 * @description 抽象搜索策略类
 * @date 2023/06/16 23:59:00
 */
public abstract class AbstractSearchStrategy {

    public Result<Object> defaultSearch(ItemQueryRequest params) {
        //校验参数

        //构建搜索参数
        ItemQuery itemQuery = buildRequest(params);
        if(itemQuery == null){
            return Result.error(ResultCode.EXCEPTION);
        }
        //查询结果
        return searchResult(itemQuery);
    }

    /**
     * 构建参数
     * @param params
     * @return
     */
    public abstract ItemQuery buildRequest(ItemQueryRequest params);

    /**
     * 查询参数
     * @param itemQuery
     * @return
     */
    public abstract Result<Object> searchResult(ItemQuery itemQuery);

    /**
     * 获取策略类型
     * @return
     */
    public abstract String getType();
}
