# data-query

详见单元测试[QueryTest.java](src/test/java/io/github/brightloong/data/query/core/QueryTest.java)

- 时间比较紧，未实现完整的and、or逻辑，只做了简单实现
- 执行顺序where、groupBy、orderBy
- groupBy 会默认取groupBy后的第一条数据