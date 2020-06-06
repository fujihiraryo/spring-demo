# SpringBootのサンプル

## Spring JPAを使ったデータベースアクセス

データベースに接続した状態で起動するには最低限以下の3つが必要

- ローカルでmysqlを起動

- build.gradleのdependenciesに以下を記載

```gradle
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
runtimeOnly 'mysql:mysql-connector-java'
```

- application.propertiesに接続情報を記載

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/demodb
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.jpa.database=MYSQL
spring.jpa.hibernate.ddl-auto=none
```

起動時にテーブルを自動で作成するには以下の2つが必要

- schema.sqlにCREAETE文を記載

```sql
DROP TABLE `demo_table`
CREATE TABLE `demo_table`
(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR (100) DEFAULT NULL
)
```

- テーブルに対応するEntityを作成

```java
@Entity
@Table(name = "demo_table")
public class DemoEntity {
    @Id
    private Integer id;
    private String name;
}
```

GETメソッドでテーブルのレコード全件取得

- Controllerにメソッドを追加

```java
demoRepository.findAll()
```

- EntityのidフィールドにGeneratedValueをつけないとidが自動で生成されない

```bash
curl -X GET -H 'Content-Type:application/json' localhost:8080/demo/api
```

POSTメソッドでテーブルにレコード追加

- Controllerにメソッド追加

```java
demoRepository.save(demoEntity)
```

```bash
curl -X POST -H 'Content-Type:application/json' -d '{"name":"xxx"}' localhost:8080/demo/api
```

## Thymeleafによるレンダリング

リクエストに対してhtmlのページを返す

- htmlファイルをresources/templates以下に置く
- Controllerでhtmlの名前を返す

GETメソッドで全件取得し、ページ上に表示

- ControllerでModelに全件取得したEntityのリストをセットする
- html上でth:eachを使い、1件ずつ表示する

Saveボタンを表示し、フォームに入力された情報を保存する

- ControllerでGetされたときにModelにフォームをセットする
- html上に入力欄を表示し、入力されたデータをフォームにセットする
- PostされたらControllerでフォームを受け取り、Repositoryによりsaveする
- 再びGetされたときのhtmlを返す
