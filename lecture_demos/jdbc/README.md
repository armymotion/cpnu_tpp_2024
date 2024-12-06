#

1. download template

- [Link to preconfigured start.spring.io](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.3.4&packaging=jar&jvmVersion=21&groupId=ua.cn.stu&artifactId=univer02&name=univer02&description=Spring%20Boot%20project%20for%20university&packageName=ua.cn.stu.univer02&dependencies=native,devtools,docker-compose,modulith,web,data-jdbc,postgresql,spring-ai-vectordb-pgvector,spring-ai-openai,lombok)
- Unarchive, open folder in terminal and in IDE
- run -> exception
- comment openai deps in pom.xml -> run -> no exception

2. Start DB via docker run in terminal
   (if windows -> install WSL2)
   (if no docker → [download](https://docs.rancherdesktop.io/getting-started/installation/), install, start)

   ```
   docker compose up
   ```

3. Configure DB Access in java app

- dockerfile port: "5432:5432”
- application.properties:
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
  spring.datasource.username=myuser
  spring.datasource.password=secret
  # # spring.jpa.hibernate.ddl-auto=update
  # logging.level.org.springframework.jdbc.core=TRACE
  # logging.level.org.springframework.data=DEBUG
  ```

4. install db plugin to IDE OR use other client

- intellij - available only commercial or education version
- eclipse - DBeaver
- vscode - Database Client + Database Client JDBC
- linux terminal
  ```
  pgcli -p 5432 -h localhost  mydatabase -U myuser -W
  ```

5. Init DB

- sql file in sql_schema/db_init.sql
