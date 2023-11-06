package cn.mmc8102.springboot.utils;

import cn.mmc8102.springboot.common.Constants;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author wangli
 * @description TODO
 * @date 2023/11/06 21:19:00
 */
@Slf4j
public class FutureUtil {

    /**
     * 分组并行执行
     * @return
     * @param <V>
     * @param <R>
     */
    public static <V, R> List<R> parallelApply(ThreadPoolTaskExecutor executor, List<V> list, Integer pageSize, Function<List<V>, R> function) {
        if (CollectionUtils.isEmpty(list)){
            return Collections.EMPTY_LIST;
        }
        List<List<V>> groupList = Lists.partition(list, pageSize);
        return parallelApply(executor, groupList, function);
    }


    public static <V, R> List<R> parallelApply(ThreadPoolTaskExecutor executor, Collection<V> list, Function<V, R> function) {
        if (CollectionUtils.isEmpty(list)){
            return Collections.EMPTY_LIST;
        }
        List<CompletableFuture<R>> futureList = new ArrayList<>(list.size());
        list.stream().forEach(item -> {
            CompletableFuture<R> future = CompletableFuture.supplyAsync(() -> function.apply(item), executor);
            futureList.add(future);
        });

        List<R> resultList = new ArrayList<>();
        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
            completableFuture.get(Constants.THOUSAND, TimeUnit.MILLISECONDS);
            resultList = getFutureValueListAll(futureList, Constants.THOUSAND, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            log.error("FutureUtil.parallelApply {}", e);
        }
        return resultList;
    }


    /**
     * 获取数据
     * @param futures
     * @param <T>
     * @return
     */
    public static <T> List<T> getFutureValueListAll(List<CompletableFuture<T>> futures, Integer timeOut, TimeUnit unit) {
        List<T> results = Lists.newArrayList();
        for (CompletableFuture<T> future : futures) {
            try {
                T futureValue = future.get(timeOut, unit);
                if (!Objects.isNull(futureValue)){
                    results.add(futureValue);
                }
            } catch (Exception e) {
                log.error("FutureHelper.getFutureValueList error {}", e);
            }
        }
        return results;
    }
}
