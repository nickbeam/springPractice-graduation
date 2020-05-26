DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
    ('User', 'user@yandex.ru', 'password'),
    ('Admin', 'admin@gmail.com', 'admin'),
    ('Gourmet', 'gourmet@gmail.com', 'pwd'),
    ('TestUser', 'testuser@gmail.com', 'test');

INSERT INTO user_roles (role, user_id) VALUES
    ('ROLE_USER', 100000),
    ('ROLE_USER', 100001),
    ('ROLE_ADMIN', 100002),
    ('ROLE_USER', 100003);

INSERT INTO restaurants (name, address, phone, email)
VALUES ('Бриллиантовая рука', 'Невский д.55', '+7(812)3223232', 'info@br.ru'),
       ('Чиллаут снек бар', 'Большой Сампсониевский д.4', '+7(812)5558877', 'info@chill.ru'),
       ('Элит', 'Набережная реки Мойки д.20', '+7(812)4448899', 'info@elite.ru');

INSERT INTO dishes (restaurant_id, name, price, date)
VALUES (100004, 'Стейк', 1000, CURRENT_DATE),
       (100004, 'Мясо по французски', 750, CURRENT_DATE),
       (100004, 'Сибас', 590, CURRENT_DATE),

       (100005, 'Гренки', 150, CURRENT_DATE),
       (100005, 'Чипсы', 90, CURRENT_DATE),
       (100005, 'Нагитсы', 210, CURRENT_DATE),

       (100006, 'Оленина', 900, CURRENT_DATE),
       (100006, 'Карп', 1200, CURRENT_DATE),
       (100006, 'Лобстер', 1500, CURRENT_DATE);

SET timezone TO 'Europe/Moscow';
INSERT INTO votes (user_id, restaurant_id, date_time)
VALUES (100000, 100004, current_date + interval '10 hour'),
       (100001, 100004, current_date + interval '11 hour'),
       (100002, 100006, current_date + interval '16 hour');
