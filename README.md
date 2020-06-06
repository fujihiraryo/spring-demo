# SpringBootのサンプル

- Spring JPAによるデータベースアクセス
- Thymeleafによるページレンダリング
- RESTful APIの場合とWebページの場合それぞれで全件取得と登録
- エラーハンドリング、バリデーションチェックはやってない

## Spring JPAを使ったデータベースアクセス

データベースに接続した状態で起動するには最低限以下の3つが必要

- ローカルでmysqlを起動
- demodbというスキーマが作成されている状態
- build.gradleのdependenciesに以下を記載
- application.propertiesに接続情報を記載

起動時にテーブルを自動で作成するには以下の2つが必要

- schema.sqlにCREAETE文を記載
- テーブルに対応するEntityを作成

GETメソッドでテーブルのレコード全件取得

```bash
curl -X GET -H 'Content-Type:application/json' localhost:8080/demo/api
```

POSTメソッドでテーブルにレコード追加

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
