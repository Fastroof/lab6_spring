ЛР6

Роботу виконали студенти групи ІО-02:
- Воловик Олександр
- Литвиненко Данило
- Шумельчук Юрій

Варіант 16:
><b>Тема:</b> Оренда квартир
<br><b>Сутності:</b> Квартира, параметри квартири, опис
<br><b>Актори:</b> Хазяїн квартири, клієнт 
<br><b>Сценарії використання:</b> 
<br>Хазяїн квартири: Створення/редагування/видалення інформації про квартиру
<br>Клієнт: пошук квартири по параметрам

Тестування розробленого API на відповідність опису у документації за допомогою Postman:

GET /api/orders

![Screenshot](readme/GET_orders_doc.png)
![Screenshot](readme/GET_orders_200.png)

GET /api/rooms (Фільтрація по параметрах та пагінація)

![Screenshot](readme/GET_rooms_doc.png)
![Screenshot](readme/GET_rooms_200.png)
![Screenshot](readme/GET_rooms_200_filtered.png)
![Screenshot](readme/GET_rooms_200_filtered2.png)
![Screenshot](readme/GET_rooms_200_paginated.png)

POST /api/rooms

![Screenshot](readme/POST_rooms_doc.png)
![Screenshot](readme/POST_rooms_200.png)
![Screenshot](readme/POST_rooms_400.png)
![Screenshot](readme/POST_rooms_401.png)

GET /api/rooms/{id}

![Screenshot](readme/GET_rooms_id_doc.png)
![Screenshot](readme/GET_rooms_id_200.png)
![Screenshot](readme/GET_rooms_id_404.png)

PUT /api/rooms/{id}

![Screenshot](readme/PUT_rooms_id_doc.png)
![Screenshot](readme/PUT_rooms_id_200.png)
![Screenshot](readme/PUT_rooms_id_400.png)
![Screenshot](readme/PUT_rooms_id_401.png)
![Screenshot](readme/PUT_rooms_id_404.png)

DELETE /api/rooms/{id}

![Screenshot](readme/DELETE_rooms_id_doc.png)
![Screenshot](readme/DELETE_rooms_id_200.png)
![Screenshot](readme/DELETE_rooms_id_401.png)
![Screenshot](readme/DELETE_rooms_id_404.png)

Контрольні питання:

1. Що таке ORM?
2. В чому полягає різниця між JPA та Hibernate?
3. Поясніть призначення кожного методу з інтерфейсу CrudRepository.
4. Яким вимогам має відповідати @Entity-клас?
5. Які є типи відношень між сутностями у JPA?
6. Для чого потрібні DTO? Чому замість них не завжди можна використовувати @Entity об’єкти?