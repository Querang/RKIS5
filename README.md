Борисюк Кирилл Алексеевич КИ21-16/2б
РКИС Лабораторная работа №5
Вариант-3


Инструкция запуска из консоли.

Скачайте проект или выполните команду:
```
git clone https://github.com/Querang/RKIS5.git
```

В консоли перейти в папку с проектом, после:
1) Создание базы данных
```
psql -U postgres -h localhost -f car_db.sql  
```
2) Собрать проект при помощи команды:
```
.\mvnw.cmd package
```
3) Запустить программу:
```
java -jar target/RKIS5-0.0.1-SNAPSHOT.jar 
```
4) Открывать [страницу localhost](http://127.0.0.1:8080)

Для сборки необходим Maven - https://maven.apache.org/download.cgi