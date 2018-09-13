# spring-cloud
spring cloud examples


Spring cloud Eureka 服务治理(高可用服务中心) 
Eureka Server 的高可用实际上就是将自己作为服务向其它服务注册中心注册自己，这样就形成了一组互相注册的服务中心，以实现服务清单 的互相同步，达到高可用的效果。

#三个Eureka server节点的实例主机名需要不一样，否则注册不成功
eureka.instance.hostname=peer1
eureka.instance.hostname=peer2
eureka.instance.hostname=peer3

#单机上需要添加主机名
C:/windows/system32/drivers/etc找到hosts文件
#添加主机名
127.0.0.1 peer1 peer2 peer3  


##business-gateway
针对web请求的独立网关，未与注册中心集成，可独立部署，包含：session、token防重等处理；