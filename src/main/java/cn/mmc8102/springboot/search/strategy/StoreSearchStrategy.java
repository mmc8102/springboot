package cn.mmc8102.springboot.search.strategy;

import cn.mmc8102.springboot.search.common.Result;
import cn.mmc8102.springboot.search.enums.SearchTypeEnum;
import cn.mmc8102.springboot.search.request.ItemQuery;
import cn.mmc8102.springboot.search.request.ItemQueryRequest;
import org.springframework.stereotype.Component;

/**
 * @author wangli
 * @description 门店搜索策略类
 * @date 2023/06/17 00:09:00
 */
@Component("storeSearch")
public class StoreSearchStrategy extends AbstractSearchStrategy{


    @Override
    public ItemQuery buildRequest(ItemQueryRequest params) {
        if(params == null || params.getStoreId() == null){
            return null;
        }
        ItemQuery itemQuery = new ItemQuery();
        itemQuery.setStoreId(params.getStoreId());
        return itemQuery;
    }

    @Override
    public Result<Object> searchResult(ItemQuery itemQuery) {
        System.out.println("===StoreSearchStrategy start===");
        System.out.println("storeId=" + itemQuery.getStoreId());
        System.out.println("===StoreSearchStrategy end===");
        System.out.println();


        return Result.success(SearchTypeEnum.STORE.getType());
    }

    @Override
    public String getType() {
        return SearchTypeEnum.STORE.getType();
    }
}
