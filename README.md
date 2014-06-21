hotel-booking
=============

##Общее описание##

Система бронирования отелей.
Использованные технологии: Maven, Spring, Spring Security, Hibernate, JSF,
PrettyFaces, PrimeFaces, GMaps4JSF, Jetty, MySQL.

##Запуск##

Для запуска необходимо выполнить [скрипт инициализации БД](src/main/resources/database.sql).
Затем запусть сборку и деплой: **mvn jetty:run**

##Основная функциональность##

Реализованы 4 веб-формы: 
 - Просмотр списка отелей (список упорядочен по количеству комментариев на странице отеля,
 есть возможность поиска по названию отеля);
 - Просмотр подробной информации об отеле (просмотр подробных сведений об отеле,
 карты с расположением отеля,  редактирование, удаление отеля, добавление и удаление комментариев,
 бронирование отеля);
 - Формы добавления и редактирования отеля (практически идентичные).
 
##Роли пользователей##

Администратор (ROLE_ADMIN): имеет возможность просматривать, добавлять, удалять, редактировать отели,
а также удалять любые комментарии к отелям. Тестовые логин/пароль - admin/admin

Пользователь (ROLE_USER): имеет возможность просматривать отели, добавлять комментарии
и удалять только свои комментарии. Тестовые логин/пароль - user/user

Анонимный пользователь (ROLE_ANONYMOUS): имеет возможность просматривать отели, добавлять комментарии.