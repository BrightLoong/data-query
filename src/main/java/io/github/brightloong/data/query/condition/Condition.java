package io.github.brightloong.data.query.condition;

import java.util.List;

/**
 * @author BrightLoong
 * @date 2021/9/11 20:51
 * @description 查询条件.
 */

public class Condition<T> {

    /**
     * where condition
     */
    private final Where<T> where;

    /**
     * orderBy conditions
     */
    private final List<OrderBy<T, ?>> orderByList;

    /**
     * groupBy conditions
     */
    private final List<GroupBy<T, ?>> groupByList;

    /**
     * limit conditions
     */
    private final int limit;


    Condition(Where<T> where, List<OrderBy<T, ?>> orderByList, List<GroupBy<T, ?>> groupByList, int limit) {
        this.where = where;
        this.orderByList = orderByList;
        this.groupByList = groupByList;
        this.limit = limit;
    }

    public static <T> Condition.ConditionBuilder<T> builder() {
        return new Condition.ConditionBuilder<>();
    }

    public static class ConditionBuilder<T> {
        private Where<T> where;
        private List<OrderBy<T, ?>> orderByList;
        private List<GroupBy<T, ?>> groupByList;
        private int limit;

        ConditionBuilder() {
        }

        public Condition.ConditionBuilder<T> where(Where<T> where) {
            this.where = where;
            return this;
        }

        public Condition.ConditionBuilder<T> orderByList(List<OrderBy<T, ?>> orderByList) {
            this.orderByList = orderByList;
            return this;
        }

        public Condition.ConditionBuilder<T> groupByList(List<GroupBy<T, ?>> groupByList) {
            this.groupByList = groupByList;
            return this;
        }

        public Condition.ConditionBuilder<T> limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Condition<T> build() {
            return new Condition<>(this.where, this.orderByList, this.groupByList, this.limit);
        }
    }

    public Where<T> getWhere() {
        return where;
    }

    public List<OrderBy<T, ?>> getOrderByList() {
        return orderByList;
    }

    public List<GroupBy<T, ?>> getGroupByList() {
        return groupByList;
    }

    public int getLimit() {
        return limit;
    }
}
