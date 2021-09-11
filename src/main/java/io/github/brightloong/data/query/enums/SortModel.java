package io.github.brightloong.data.query.enums;

/**
 * @author BrightLoong
 * @date 2021/9/11 21:05
 * @description
 */
public enum SortModel {

    /**
     * 正序.
     */
    ASC(1, "正序"),

    /**
     * 倒序.
     */
    DESC(2, "逆序");

    private int type;

    private String description;

    SortModel(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
