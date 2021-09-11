package io.github.brightloong.data.query.core;

import io.github.brightloong.data.query.condition.Condition;
import io.github.brightloong.data.query.condition.GroupBy;
import io.github.brightloong.data.query.condition.OrderBy;
import io.github.brightloong.data.query.condition.Where;
import io.github.brightloong.data.query.enums.SortModel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author BrightLoong
 * @date 2021/9/11 21:20
 * @description
 */
public class Query<T> {

    private final List<T> datas;

    public Query(List<T> datas) {
        this.datas = datas;
    }

    /**
     * 按照where、groupBy、orderBy的顺序执行，groupBy默认取第一个.
     * @param condition condition
     * @return 执行结果
     */
    public List<T> queryWithCondition(Condition<T> condition) {
        if (datas == null || datas.size() == 0) {
            return Collections.emptyList();
        }
        List<T> list = datas;
        list = executWhere(condition.getWhere(), list);
        list = executeGroupBy(condition.getGroupByList(), list);
        list = executOrderBy(condition.getOrderByList(), list);
        return list;
    }

    /**
     * 执行where.
     * @param where
     * @param list
     * @return
     */
    private List<T> executWhere(Where<T> where, List<T> list) {
        if (Objects.nonNull(where)) {
            list = list.stream().filter(t -> {
                boolean andResult = where.getAndFunctions().stream().allMatch(function -> function.apply(t));
                boolean orResult = where.getOrFunctions().stream().anyMatch(function -> function.apply(t));
                return andResult || orResult;
            }).collect(Collectors.toList());
        }

        return list;
    }

    /**
     * 执行orderBy
     * @param orderByList
     * @param list
     * @return
     */
    private List<T> executOrderBy(List<OrderBy<T, ?>> orderByList, List<T> list) {
        if (Objects.nonNull(orderByList)) {
            for (OrderBy<T, ?> orderBy : orderByList) {
                list = list.stream().sorted(orderBy.getSortModel().equals(SortModel.ASC) ? orderBy.getComparator() :
                        orderBy.getComparator().reversed()).collect(Collectors.toList());
            }
        }

        return list;
    }

    /**
     * 执行groupBy.
     * @param groupByList
     * @param list
     * @return
     */
    private List<T> executeGroupBy(List<GroupBy<T, ?>> groupByList, List<T> list) {
        for (GroupBy<T, ?> groupBy : groupByList) {
            Map<?, List<T>> groupByResult = list.stream().collect(Collectors.groupingBy(groupBy.getGroupByfield()));
            //每次取groupBy的第一条
            List<T> newList = new ArrayList<>(groupByList.size());
            groupByResult.forEach((k, v) -> newList.add(v.get(0)));
            list = newList;
        }

        return list;
    }
}
