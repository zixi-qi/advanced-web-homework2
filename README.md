# 高级Web Homework2

+ 项目说明

传统的关系型数据库在处理社交网络数据等网状数据时存在着较大的劣势，例如如果我们希望在数据库中的人际关系网
络图中找到两个人之间最短的由朋友关系组成的路径，需要在关系表中进行多次查询，甚至将全部关系数据读入内存再
应用最短路算法。在图数据库中，数据项间关系的表达并非通过存储外键，而是通过类似存储指向对应条目地址的指针
来实现，因此在处理上述情况时具有较大的性能优势。

本项目使用Spring Boot和Neo4j图数据库实现了一个基于图的人际关系网络表示，支持个人（条目）的添加，删除，
以及朋友关系的创建和最短朋友关系路径的查询。

+ 架构和接口说明

项目使用了Spring项目的Entity->Repository->Service->Controller架构。

在test.java.com.advancedweb.servicetest.TestPersonService中对业务逻辑进行了测试。

Controller层将业务逻辑封装为了Restful接口

+ Github地址：

https://github.com/QizixiAlex/advancedweb_homework2

+ 作者

齐梓熙 13307110132