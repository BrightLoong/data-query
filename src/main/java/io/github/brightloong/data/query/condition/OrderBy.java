package io.github.brightloong.data.query.condition;

import io.github.brightloong.data.query.enums.SortModel;

import java.util.Comparator;
import java.util.function.Function;

/**
 * @author BrightLoong
 * @date 2021/9/11 20:52
 * @description OrderBy Condition, <A, B> A 查询实体，B排序字段.
 */
public class OrderBy<A, B> {

    private final Comparator<A> comparator;

    private final SortModel sortModel;


    /**
     * A 实体， B排序字段，默认正序.
     * @param comparator 排序字段的排序规则
     * @param sortField 排序字段,get function
     */
    public OrderBy(Comparator<B> comparator, Function<A, B> sortField) {
       this(comparator, sortField, SortModel.ASC);
    }


    /**
     *
     * A 实体， B排序字段.
     * @param comparator 排序字段的排序规则
     * @param sortField 排序字段,get function
     * @param sortModel {@link SortModel}
     */
    public OrderBy(Comparator<B> comparator, Function<A, B> sortField, SortModel sortModel) {
        this.sortModel = sortModel;
        this.comparator = (o1, o2) -> comparator.compare(sortField.apply(o1), sortField.apply(o2));
    }

    public Comparator<A> getComparator() {
        return comparator;
    }

    public SortModel getSortModel() {
        return sortModel;
    }
}
