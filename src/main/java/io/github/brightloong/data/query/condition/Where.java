package io.github.brightloong.data.query.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author BrightLoong
 * @date 2021/9/11 20:52
 * @description wehre condition, T 查询实体
 */
public class Where<T> {

    /**
     * 保存多个 and 条件
     */
    private final List<Function<T, Boolean>> andFunctions = new ArrayList<>();

    /**
     * 保存多个 or 条件
     */
    private final List<Function<T, Boolean>> orFunctions = new ArrayList<>();


    /**
     * 添加and，可多次添加
     * @param function 具体条件
     * @return Where
     */
    public Where<T> addAnd(Function<T, Boolean> function) {
        andFunctions.add(function);
        return this;
    }

    /**
     * 添加or，可多次添加
     * @param function 具体条件
     * @return Where
     */
    public Where<T> addOr(Function<T, Boolean> function) {
        orFunctions.add(function);
        return this;
    }

    /**
     * getAndFunctions.
     * @return 返回and条件集合
     */
    public List<Function<T, Boolean>> getAndFunctions() {
        return andFunctions;
    }

    /**
     * getOrFunctions
     * @return 返回or条件集合
     */
    public List<Function<T, Boolean>> getOrFunctions() {
        return orFunctions;
    }


}
