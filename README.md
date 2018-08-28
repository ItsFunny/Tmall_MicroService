
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
	miss somebody,hah.. 
