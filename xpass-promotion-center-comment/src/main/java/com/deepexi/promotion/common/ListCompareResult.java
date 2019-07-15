package com.deepexi.promotion.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 在一对多的情况下，
 * 用于获取“多”这一方在更新时的新增/更新/删除三种列表
 * @See ListCompareResultTest.test()
 *
 * @author zhoust
 * @date 2019/5/24
 **/
@Data
public class ListCompareResult<T> {
    public static final String TO_CREATE = "TO_CREATE";
    public static final String TO_UPDATE = "TO_UPDATE";
    public static final String TO_DELETE = "TO_DELETE";
    public static final String TO_HOLD = "TO_HOLD";

    private List<T> toCreate = new ArrayList<>(10);
    private List<T> toUpdate = new ArrayList<>(10);
    private List<T> toDelete = new ArrayList<>(10);
    private List<T> toHold = new ArrayList<>(10);

    public static <T> ListCompareResult<T> compareLists(List<T> oldList,
                                                        List<T> updateList,
                                                        Function<T, Object> keyGetter,
                                                        BiFunction<T, T, Boolean> shouldUpdate) {
        Map<Object, T> oldMap = oldList.stream().collect(Collectors.toMap(keyGetter, x -> x));
        Map<Object, T> updateMap = updateList.stream()
                .filter(x -> keyGetter.apply(x) != null)
                .collect(Collectors.toMap(keyGetter, x -> x));
        Map<String, List<T>> toDeleteOrUpdateMap = oldList.stream().collect(Collectors.groupingBy(cur -> {
            T update = updateMap.get(keyGetter.apply(cur));
            if (update == null) {
                return ListCompareResult.TO_DELETE;
            } else if (shouldUpdate.apply(cur, update)) {
                return ListCompareResult.TO_UPDATE;
            }
            return ListCompareResult.TO_HOLD;
        }, Collectors.toList()));
        List<T> toCreate = updateList.stream()
                .filter(x -> !oldMap.containsKey(keyGetter.apply(x)))
                .collect(Collectors.toList());
        List<T> toDelete = toDeleteOrUpdateMap.get(TO_DELETE);
        List<T> toUpdate = toDeleteOrUpdateMap.get(TO_UPDATE);
        List<T> toHold = toDeleteOrUpdateMap.get(TO_HOLD);
        ListCompareResult<T> result = new ListCompareResult<>();
        if (toCreate != null) {
            result.setToCreate(toCreate);
        }
        if (toUpdate != null) {
            result.setToUpdate(toUpdate);
        }
        if (toDelete != null) {
            result.setToDelete(toDelete);
        }
        if (toHold != null){
            result.setToHold(toHold);
        }
        return result;
    }

}
