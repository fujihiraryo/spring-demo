# SpringBootのサンプル

- Spring JPAによるデータベースアクセス
- Thymeleafによるページレンダリング
- RESTful APIの場合とWebページの場合それぞれで全件取得と登録
- エラーハンドリング、バリデーションチェックはやってない

## 構成

![Screen Shot 2020-06-06 at 20 10 55](https://user-images.githubusercontent.com/49140016/83942882-9d5a6680-a832-11ea-965a-f8894cfd862d.png)

## Spring JPAを使ったデータベースアクセス

データベースに接続した状態で起動するには最低限以下の3つが必要

- ローカルでmysqlを起動
- demodbというスキーマが作成されている状態
- build.gradleのdependenciesに以下を記載

```gradle
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
runtimeOnly 'mysql:mysql-connector-java'
```

- application.propertiesに接続情報を記載
- `spring.jpa.hibernate.ddl-auto=always` だとidがauto_incrementにならなかった

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

- EntityのidフィールドにGeneratedValueをつけないとidが自動採番されない

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

```java
List<DemoEntity> demoEntities = demoRepository.findAll();
model.addAttribute("demoEntities", demoEntities);
```

- html上でth:eachを使い、1件ずつ表示する

```html
<th:block th:each="entity: ${demoEntities}">
    [[${entity.id}]] [[${entity.name}]] <br>
</th:block>
```

Saveボタンを表示し、フォームに入力された情報を保存する

- ControllerでGetされたときにModelにフォームをセットする

```java
@GetMapping
public String findAll(Model model) {
    List<DemoEntity> demoEntities = demoRepository.findAll();
    model.addAttribute("demoEntities", demoEntities);
    model.addAttribute("newEntity", new DemoEntity());
    return "demo.html";
}
```

- html上に入力欄を表示し、入力されたデータをフォームにセットする

```html
<form method="post" th:object="${newEntity}">
    <input type="text" th:field="*{name}" />
    <input type="submit" value="Save" />
</form>
```

- PostされたらControllerでフォームを受け取り、Repositoryによりsaveする
- 再びGetされたときのhtmlを返す

```java
@PostMapping
public String save(DemoEntity demoEntity, Model model) {
    demoRepository.save(demoEntity);
    return findAll(model);
}
```
