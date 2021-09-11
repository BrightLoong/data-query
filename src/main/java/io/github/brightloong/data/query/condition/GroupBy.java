package io.github.brightloong.data.query.condition;

import java.util.function.Function;

/**
 * @author BrightLoong
 * @date 2021/9/11 20:53
 * @description
 */
public class GroupBy<A, B> {

    /**
     * groupBy字段get方法.
     */
    private final Function<A, B> groupByfield;

    public GroupBy(Function<A, B> groupByfield) {
        this.groupByfield = groupByfield;
    }

    /**
     * get method
     * @return groupByfield
     */
    public Function<A, B> getGroupByfield() {
        return groupByfield;
    }
}
