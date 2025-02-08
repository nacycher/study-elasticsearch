## 初识es与mysql的概念对比

### 索引
是es中相同类型的文档的集合，类似于mysql中的表。
- es是以json格式存储文档的，mysql是以表的形式存储文档的。
![](../images/part1/elasticsearch-04-01.png)

![](../images/part1/elasticsearch-04-02.png)

- mysql擅长数据库事务操作，可以确保数据的安全、一致性。
- es擅长海量数据的存储和检索，但是不擅长数据库事务操作。
![](../images/part1/elasticsearch-04-03.png)