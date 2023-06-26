yaml 
- YAML, 一种数据序列化格式
- 优点: 
  - 容易阅读
  - 容易与脚本语言交互
  - 以数据为核心, 重数据轻格式
- YAML文件扩展名
  - .yml(主流)
  - .yaml

打包实现命令行切换生产环境
java -jar jar包名称.jar --spring.profiles.active=test

修改端口
java -jar jar包名称.jar --server.port=88

按优先级一起来配置
java -jar jar包名称.jar --server.port=88 --spring.profiles.active=test

boot听从maven的命令, maven配置变量属性给boot

配置文件外部生效必须放在jar包的同级目录
config/application.yml 的生效级别最高

SpringBoot整合 JUnit