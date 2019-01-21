* 2018-11-14 更:
    *   20号要去北京了,最近一直在学Go,Java先放一放了,Java i wont give up,应该等Go稳固下来这个project会重启,到时候一些api会用Go来写,至于前端,not sure yet,是一体还是分离暂定吧,一体的话肯定用Java的template,Go的template 忒蛋疼了,分离的话用node.js吧或react.js<br>
*   2018-10-27 更:
    *   今天尝试重写一个分库分表demo的时候发现原先写的这个SQLExtention小结构的体系已经忘了..记得写文档....SQLExtention结构原先只有我和god知道啥意思,现在怕是只有god知道了,重新回顾下看看,然后尝试编写文档<br>


---

*   第三次开发,基于时间原因仅完成了部分,第一次开发采用的纯粹的单体架构,以SpringBoot为基础,代码写的很幼稚(因为写这个的时候只知道Java基础,什么是Spring都不知道),第二次开发是基于SSM的伪分布式开发(未涉及SOA,仅仅只是rest调用),代码质量有所提升,但是依然稚嫩,此次开发基于时间及其他项目缘故,期间更是有遇到忘记push而代码重写的情况,因而只完成了部分(同时部分代码放到了Spring-test repo中),代码质量较上次亦有提升(时间不够尚未优化已完成的代码质量),[可以参考dlxy这个项目,代码经过了部分优化,易扩展,易维护,就是controller中的参数校验部分需要理解一下才行,主要是dlxy-system和dlxy-batch-system这2个project](https://github.com/ItsFunny/dlxy)
关于分库分表,未使用mycat,自己尝试编写的,尚未有问题(CRUD),当然可能未测试完全,至少crud之后还是ok,核心在library-project 下的sqlextention包中,核心是通过map存储,配合order注解,然后通过String强字符串匹配(这是个缺陷,因为要设置的参数好像>3)然后通过proxy代理dao 管理某个表的所有分库对应的dao类,内部又通过取模的方式取得表名,如果想通过另外一种策略:通过划分id 插入表的形式的话,新增一个抽象类.<br>
数据一致性采用的是MQ,异常处理借鉴的是Github中的一个开源项目passcloud,统一定义异常码.
重复的CRUD太多,dao抽出公共的dao,Service也可以抽出,service的代码还未优化,tm-system-admin 中的other 是临时包,还未删除
基础架构图:
*    ![base_design_png](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design.png)<br>
*   服务及其系统间的调用方式:<br>
    *   ![call_logic](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design_img/call_logic.png)<br>
---
* 技术选型:以SpringBoot为基础框架,Spring-cloud-eureka作为服务注册中心,Spring-cloud-config作为服务配置中心,Spring-cloud-zuul作为服务网关,spring-cloud-hystrix作为服务熔断处理工具,spring-cloud-hystrix-dashboard服务检测 采用RabbitMQ作为消息中间件
* 持久层:Mybatis||DButils||JDBCTemplate
* 表现层:Freemarker
* 缓存:Redis,Cache
* 权限控制:shiro
* 搜索引擎:solr 而非ElastricSearch
* 预计附加:多线程处理消息
* 项目最后阶段预计添加nginx 
---
* 角色的划分:</br>
    店铺下的角色统一,不然太复杂了(天猫的私人订制的) admin->subAdmin->employee</br>
    后台管理下的角色:superAdmin,subSuperAdmin </br>
    普通角色:user</br>
    应该还有其他的角色</br>
    superAdmin>subSuperAdmin>admin>subAdmin>employee =user</br>
部分设计思路图:
* SSO流程的思路图:<br>
    -   ![simple_login_procession](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design_img/login_procession.png)<br>
-   分库分表的类层次结构图v0.1(未优化)<br>
    -   ![数据库扩展类层次结构](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design_img/sql_extention_class.png)
-   分布式下的数据一致性:无论是上游服务器还是下游服务器都需要开放一个消息接口供消息服务器查询状态,确保一致<br>
    -   上游服务的流程<br>
        -   ![top_service_logic](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design_img/data_consistent.png)
    -   下游服务器的接收流程:<br>
        -   ![down_logic](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design_img/down_logic_consistent.png)<br>
-   2018-10-2
  - 项目设计中的一些点
  - 商品模块:商品模块数据库中最头疼的莫过于spu,sku这块,个人理解spu是某类商品或某类下的某种商品的属性集合 而sku则是具体某个商品的属性集合<br>
  spu表中的部分参数:spu_id,spu_property_ids(这是一个String类型的,存放的是propertyId,用逗号分隔)....<br>
  sku:sku_id,spu_id,sku_property_values(也是一个字符串,并且与spu中的property_ids是对应的关系),stock,price..<br>
可以发现spu:sku=1:n spu:property=1:n,sku:property_value=1:n..<br>
2018-06-13</br>
  - 15:00
    - 开始编写商铺后台管理,简单做了下功能图和角色图:(因为本人没有做过淘宝兼职,因此只是很low 的版本v0.2)</br>
    ![simple_management_role_png](https://github.com/ItsFunny/Tmall_MicroService/blob/master/tmall-sys-design-imgs/management-role.png)</br>
    - 角色功能图:</br>
        - ![simple_management_role_resource_png](https://github.com/ItsFunny/Tmall_MicroService/blob/master/tmall-sys-design-imgs/management-roel-resource.png)
    - 商品数据库关系图(数据库中我记得都改了很多,但是图找不到了,mac下也不能用pd,就直接还是这张图了):</br>
        - ![simple_product_sql_relationship](https://github.com/ItsFunny/Tmall_MicroService/blob/master/tmall_sql_design_img/product-v0.2.png)
- 2018-07-25
    -   Decide to dev admin_system firstlly	   BaseFunction:v0.1 </br>
    -    ![simple_admin_system_base_function](https://github.com/ItsFunny/Tmall_MicroService/blob/master/design_img/admin_design.png)

