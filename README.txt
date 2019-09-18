项目初始化步骤：
一：application.yml文件调整：
    1、调整dialect配置配置为正确的目录
    2、调整logging.level.config的配置，指向绝对地址
    3、调整DB链接，用户名，密码
    4、调整spring.cache.ehcache.config，指向绝对地址
    5、如果有双节点则双节点中的snowflake.workerId需要不同
    6、项目上下文为：spring.application.name

二、build.gradle文件调整：
    1、调整
        jar {
            baseName = 'upfdemo'
            excludes = ["*.yml","*.properties*","*.xml"]
        }
       其中baseName为jar包的名字

三、.gitlab-ci.yml文件调整
    1、调整variables中的配置，已修改打包的位置等配置

四、settings.gradle文件调整
    1、调整rootProject.name 为项目名字

五、定时任务框架：
    1、测试环境访问地址：http://192.168.128.103:8090/jobs/toLogin
    2、用户名/密码：efepjob/888888a
    3、生产环境访问地址：http://192.168.207.168:8080/cepp-jobs-svr/
    4、用户名/密码：同测试
    5、配置方法：照抄
        5.1、核心配置：	GLUE配置： curl -d "name=jgtest" "http://192.168.128.108:9090/upfdemo/quartzScheduler/batchOrde/sendResult/v1.0"
        解释：以post方式向指定地址发一个请求

