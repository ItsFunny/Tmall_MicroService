# tmall_microservice
微服务化的Tmall实现

本系统根据user role来划分不同的服务:
Buyer:
	用户服务(购物车,收藏夹,浏览记录)
	登录服务(登录[微信登录,邮箱登录],注册[微信扫码注册绑定,邮箱注册])
	积分服务(积分兑换,信用积分)
  订单服务(取消订单,确认订单)
  支付服务(确认付款,确认取消付款)
Seller:
  店铺服务(店铺员工管理,店铺展示管理(product-category-crud,product-crud))
	产品服务
	订单服务(生成订单,确认取消订单)
	支付服务(退款)
Manager:

----------------------------------------------------------------------------------
技术选型:以SpringBoot为基础框架,Spring-cloud-eureka作为服务注册中心,Spring-cloud-config作为服务配置中心,Spring-cloud-zuul作为服务网关,spring-cloud-hystrix作为服务熔断处理工具,spring-cloud-hystrix-dashboard服务检测
持久层:Mybatis||DButils||JDBCTemplate
表现层:Freemarker
缓存:Redis,Cache
预计附加:多线程方面的技术




  
