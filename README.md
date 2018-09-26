
关于分库分表的核心在library-project 下的sqlextention包中,核心是通过map存储,然后通过proxy代理dao 管理某个表的所有分库对应的dao类,内部又通过取模的方式取得表名,如果想通过另外一种策略:通过划分id 插入表的形式的话,新增一个抽象类..
重复的CRUD太多,dao抽出公共的dao,Service也可以抽出,service的代码还未优化,tm-system-admin 中的other 是临时包,还未删除
本项目是第三次开发,第一次开发采用的是纯粹的SpringBoot单体系统的形式,第二次开发采用的是伪分布式(并没有涉及到服务注册等),通过rest调用的方式,第三次也就是本次开发采用SOA架构,在第二次的基础上进行了数据库分库分表,数据一致性的优化等
基础架构图:
  ![base_design_png](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design.png)
----------------------------------------------------------------------------------
技术选型:以SpringBoot为基础框架,Spring-cloud-eureka作为服务注册中心,Spring-cloud-config作为服务配置中心,Spring-cloud-zuul作为服务网关,spring-cloud-hystrix作为服务熔断处理工具,spring-cloud-hystrix-dashboard服务检测 采用RabbitMQ作为消息中间件
持久层:Mybatis||DButils||JDBCTemplate
表现层:Freemarker
缓存:Redis,Cache
权限控制:shiro
搜索引擎:solr 而非ElastricSearch
预计附加:多线程方面的技术
项目最后阶段预计添加nginx 

角色的划分:</br>
角色的划分:</br>
    店铺下的角色统一,不然太复杂了(天猫的私人订制的) admin->subAdmin->employee</br>
    后台管理下的角色:superAdmin,subSuperAdmin </br>
    普通角色:user</br>
    应该还有其他的角色</br>
    superAdmin>subSuperAdmin>admin>subAdmin>employee =user</br>
    0           1               2       3       4       5</br>
2018-06-13</br>
  15:00
    开始编写商铺后台管理,简单做了下功能图和角色图:(因为本人没有做过淘宝兼职,因此只是很low 的版本v0.2)</br>
    ![simple_management_role_png](https://github.com/ItsFunny/Tmall_MicroService/blob/master/tmall-sys-design-imgs/management-role.png)</br>
    角色功能图:</br>
    ![simple_management_role_resource_png](https://github.com/ItsFunny/Tmall_MicroService/blob/master/tmall-sys-design-imgs/management-roel-resource.png)
    商品数据库关系图:</br>
   ![simple_product_sql_relationship](https://github.com/ItsFunny/Tmall_MicroService/blob/master/tmall_sql_design_img/product-v0.2.png)
2018-07-25
Decide to dev admin_system firstlly	   BaseFunction:v0.1 </br>
   ![simple_admin_system_base_function](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design_img/admin_design.png)
Login_And__Auth_Processsion:
![simple_login_procession](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design_img/login_procession.png)
2018-08-12
