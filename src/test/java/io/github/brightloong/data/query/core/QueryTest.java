package io.github.brightloong.data.query.core;


import io.github.brightloong.data.query.common.Student;
import io.github.brightloong.data.query.condition.Condition;
import io.github.brightloong.data.query.condition.GroupBy;
import io.github.brightloong.data.query.condition.OrderBy;
import io.github.brightloong.data.query.condition.Where;
import io.github.brightloong.data.query.enums.SortModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author BrightLoong
 * @date 2021/9/11 22:10
 * @description
 */
public class QueryTest {

    private List<Student> studentList;

    @Before
    public void buildStudents() {
        studentList = new ArrayList<>();
        studentList.add(new Student(2L, "韩梅梅", 1, false));
        studentList.add(new Student(1L, "张三", 1, true));
        studentList.add(new Student(3L, "李雷", 2, true));
        studentList.add(new Student(4L, "韩梅梅", 1, false));
        studentList.add(new Student(6L, "张三", 1, true));
        studentList.add(new Student(5L, "李雷", 2, true));
        studentList.add(new Student(7L, "小花", 1, false));
        studentList.add(new Student(8L, "小明", 1, true));
    }

    @Test
    public void queryWithCondition() {
        Query<Student> studentQuery = new Query<>(studentList);
        Where<Student> where = new Where<>();
        where.addAnd(Student::getBoy).addOr(student -> student.getName().equals("小花"));

        OrderBy<Student, Long> idOrderBy = new OrderBy<>(Long::compareTo, Student::getId, SortModel.DESC);
        List<OrderBy<Student, ?>> orderByList = new ArrayList<>();
        orderByList.add(idOrderBy);

        GroupBy<Student, String> nameGroupBy = new GroupBy<>(Student::getName);
        List<GroupBy<Student, ?>> groupByList = new ArrayList<>();
        groupByList.add(nameGroupBy);

        Condition.ConditionBuilder<Student> builder = Condition.builder();
        builder.where(where)
                .orderByList(orderByList)
                .groupByList(groupByList);

        List<Student> students = studentQuery.queryWithCondition(builder.build());

        /*
        studentList.add(new Student(8L, "小明", 1, true));
        studentList.add(new Student(7L, "小花", 1, false));
        studentList.add(new Student(3L, "李雷", 2, true));
        studentList.add(new Student(1L, "张三", 1, true));
        */

        Assert.assertEquals(4, students.size());
        Assert.assertArrayEquals(students.stream().map(Student::getId).toArray(), new Long[]{8L, 7L, 3L, 1L});
    }
}