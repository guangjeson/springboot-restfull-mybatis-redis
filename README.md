基于springboot2.x，整合mybatis和redis搭建的微服务开发框架

# 工程模块
## springboot2.0-helloworld
springboot2.0入门
## springboot-api
基于springboot2搭建的服务开发框架，整合了mybatis和redis

# 项目结构

### 1. 修改数据库配置
配置文件地址：/src/main/resources/application.properties
修改相应的数据源配置，比如账号、密码等

### 2. 编译工程
在项目根目录 `springboot-restfull-mybatis-redis`，运行 maven 指令：
````
mvn clean install
````
### 3. 运行工程
右键运行工程包中 `Application` 应用启动类的 main 函数，然后在浏览器访问：
`````
http://localhost:8080/crm/api/customer/1
`````
可以看到返回的 JSON 结果：
````
{
    "id": 1,
    "orgId": 1,
    "custName": "itcarrier",
    "description": ""
}
````
