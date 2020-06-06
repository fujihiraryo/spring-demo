# SpringBootのサンプル

## Spring JPAを使ったデータベースアクセス

データベースに接続した状態で起動するには最低限以下の3つが必要

- ローカルでmysqlを起動している

- build.gradleのdependenciesに以下を記載

```text
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
runtimeOnly 'mysql:mysql-connector-java'
```

- application.propertiesに接続情報を記載

```text
spring.datasource.url=jdbc:mysql://localhost:3306/demodb
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.jpa.database=MYSQL
spring.jpa.hibernate.ddl-auto=update
```

## Thymeleafによるレンダリング
