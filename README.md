# springboot手脚架
**本项目提供了开发中常用的工具类 整合了常用依赖 从而快速开发 开箱即用**
* 提供了常用工具类JsonTool MD5 UserContext ApiResponse统一响应支持国际化 自定义业务异常 全局异常处理 以及Xss注入攻击拦截器
* 整合了Mybatis-Plus Druid Redis Spring-Cache commons-lang3 Freemarker Rocketmq Hutool等常用组件


# 常用工具 #

### 微服务 ###
* springcloud-alibaba: Spring Cloud Alibaba 致力于提供微服务开发的一站式解决方案。此项目包含开发分布式应用微服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。
* 包含 Nacos Sentinel Gateway OpenFeign Dubbo RocketMQ Seata OSS SchedulerX SMS等
* github: https://github.com/alibaba/spring-cloud-alibaba

### JsonSchema ###
* 中后台「表单/表格/图表」开箱即用解决方案 通过组件拖拽生成jsonschema 并能通过schema渲染自定义表单
* XRender: https://xrender.fun/form-render
* jsonschema校验相关博客：http://events.jianshu.io/p/b6310ea2c450

### Faas ###
* Tac: ali的一套faas平台
* github: https://github.com/alibaba/tac/blob/master/README-ch.md

### MySQL binlog 增量订阅&消费组件 ###
* canal
* github: https://github.com/alibaba/canal

### LowCodeEngine ###
* 一套面向扩展设计的企业级低代码技术体系
* github: https://github.com/alibaba/lowcode-engine

### json相关 ###
* fastjson2 性能远超过其他流行JSON库 fastjson相当于fastjson2的76.07% ;jackson则只相当于fastjson2的39.15%; gson是fastjson2的33.18%，也就是说这个最常见的反序列化场景，fastjson2的性能差不多是jackson/gson的三倍
* github: https://github.com/alibaba/fastjson2

### 数据库连接池 ###
* Druid: Druid是Java语言中最好的数据库连接池。Druid能够提供强大的监控和扩展功能。
* github: https://github.com/alibaba/druid

### 规则引擎 ###
* QLExpress: 由阿里的电商业务规则、表达式（布尔组合）、特殊数学公式计算（高精度）、语法分析、脚本二次定制等强需求而设计的一门动态脚本引擎解析工具.
* github: https://github.com/alibaba/QLExpress

### 诊断工具 ###
* Arthas: 诊断神器 在线排查问题 定位方法耗时 无需重启 动态跟踪Java代码 实时监控JVM状态。
* github: https://github.com/alibaba/arthas

### 监控告警 ###
* Prometheus 是一个开源的系统监控和报警系统，在kubernetes容器管理系统中，通常会搭配prometheus进行监控，同时也支持多种exporter采集数据，还支持pushgateway进行数据上报，Prometheus性能足够支撑上万台规模的集群。
* Grafana 是一款开源的数据可视化工具，使用 Grafana 可以非常轻松的将数据转成图表(如下图)的展现形式来做到数据监控以及数据统计。
* github: https://github.com/grafana/grafana     https://github.com/prometheus/prometheus