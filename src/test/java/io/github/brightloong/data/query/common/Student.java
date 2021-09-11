package io.github.brightloong.data.query.common;

/**
 * @author BrightLoong
 * @date 2021/9/11 22:11
 * @description
 */
public class Student {
    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 是否是男孩
     */
    private Boolean boy;

    public Student(Long id, String name, Integer grade, Boolean boy) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.boy = boy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Boolean getBoy() {
        return boy;
    }

    public void setBoy(Boolean boy) {
        this.boy = boy;
    }
}
