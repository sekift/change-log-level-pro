# change-log-level-pro 日志级别动态调整工具

[change-log-level](https://github.com/sekift/change-log-level)的升级版，由于slf4j在绑定多个实现时，只能选择最新的实现（即logback），为了使log4j与log4j2也能使用，因此将项目分拆成多个部分，可选择性地打包引入使用。

四个module分别为：

1. change-common：公共类，其他实现项目都需引入；

2. change-log4j：log4j实现；

3. change-log4j2：log4j2实现；

4. change-logback：logback实现；

# 使用帮助

详见各模块的单元测试

# 畅想未来

按照美团的使用方案，可以通过HTTP或其他RPC提供给其他项目；如果可视化还需要配置后台web或client。

因此如果需要作为中间件本程序还没有完成，但是作为项目引入仍然可以使用。

# 更新说明

**2022-06-30**

1. 精简项目

2. 解决log4j2单元测试运行报错 



**2020-08-08**

1. log4j/log4j2/logback项目分拆，可分别引入，解决上一个版本只能选择logback的错误。


































