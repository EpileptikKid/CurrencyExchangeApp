# CurrencyExchangeApp

Перед запуском застосунку перейдіть до src/main/resources/sql та виконайте команди:
1) docker build -t exchange-db-image .
2) docker run -d --name exchange-db-container -p 5432:5432 exchange-db-image
для запуску бази даних, ініціалізації необхідних таблиць та тестових даних

## Сторінки застосунку

---
![Screenshot 1](/picture/1.png)
*Головна сторінка*
---
![Screenshot 3](/picture/3.png)
*Створення операції обміну валют*
---
![Screenshot 4](/picture/4.png)
*Журнал для перегляду користувачем*
---
![Screenshot 15](/picture/15.png)
*Журнал сгрупований по кварталам та операціям*
---
![Screenshot 7](/picture/7.png)
*Перегляд курсів валют*
---
![Screenshot 8](/picture/8.png)
*Журнал для адмінського інтерфейсу*
---
![Screenshot 9](/picture/9.png)
*При видаленні операція змінює статус*
---
![Screenshot 10](/picture/10.png)
*Адмінська сторінка курсів валют*
---
![Screenshot 11](/picture/11.png)
*Зміна курсу*
---
![Screenshot 13](/picture/13.png)
*Додавання курсу*
