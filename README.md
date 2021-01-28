# spring-cloud-eureka

# 유레카 인스턴스 구성하기

클러스터 구성
- single point of failure 방지
- eureka가 다른 유레카 서버와 통신하며 작동중임을 알린다 

### 유레카 서버 구성 설정 
([https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html])

**Standalone mode**
```yaml
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false  # eureka의 registry에 등록할지 여부    - 다른 유레카 서버가 필요없어 false
    fetchRegistry: false       # registy에 있는 정보를 가져올지 여부   - 다른 유레카 서버가 필요없어 false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/  # defaultZone이 로컬 인스턴스랑 동일
  server:
    enableSelfPreservation: false  # 네트워크 장애 등으로 등록된 레지스트리와 통신이 되지 않아도, 서비스 해제되는 것을 방지하는 모드 - 프로덕션 활성. 개발 비활성
```

**Peer Awareness mode**
```yaml
---
spring:
  profiles: peer1
eureka:
  instance:
    hostname: peer1
  client:
    serviceUrl:
      defaultZone: http://peer2/eureka/

---
spring:
  profiles: peer2
eureka:
  instance:
    hostname: peer2
  client:
    serviceUrl:
      defaultZone: http://peer1/eureka/
```