hotel-booking
=============

##Общее описание##

**Система бронирования отелей.**

Использованные технологии: Maven, Spring, Spring Security, Hibernate, JSF,
PrettyFaces, PrimeFaces, GMaps4JSF, Jetty, MySQL.

##Запуск##

Для запуска необходимо выполнить [скрипт инициализации БД](src/main/resources/database.sql) (MySQL).

Затем запусть сборку и деплой: **mvn jetty:run**

##Основная функциональность##

Реализованы 4 основные веб-формы и 2 вспомогательные: 
 - Просмотр списка отелей (список упорядочен по количеству комментариев на странице отеля,
 есть возможность поиска по названию отеля, возможность посмотреть статистику по комментариям);
 - Просмотр подробной информации об отеле (просмотр подробных сведений об отеле,
 карты с расположением отеля,  редактирование, удаление отеля, добавление и удаление комментариев,
 бронирование отеля);
 - Формы добавления и редактирования отеля (практически идентичные);
 - Форма авторизации;
 - Форма просмотра статистики.
 
##Роли пользователей##

Администратор (ROLE_ADMIN): имеет возможность просматривать, добавлять, удалять, редактировать отели,
а также удалять любые комментарии к отелям. Тестовые логин/пароль - admin/admin

Пользователь (ROLE_USER): имеет возможность просматривать отели, добавлять комментарии
и удалять только свои комментарии. Тестовые логин/пароль - user/user

Анонимный пользователь (ROLE_ANONYMOUS): имеет возможность просматривать отели, добавлять комментарии.

##Описание форм##

###Форма просмотра списка отелей###

Форма представляет собой динамически подгружаемый список отелей с информацией о цене за ночь, месторасположении, телефоне (реализовано с помощью primefaces:dataScroller с ограничением 10 записей). На форме присутствует возможность поиска отеля по названию, при этом список обновляется с помощью PrimeFaces AJAX. По клику на элементе списка открывается форма просмотра информации о данном отеле.

На форме присутствует кнопка **"Добавить отель"**, доступная только администратору. По клику на эту кнопку открывается форма добавления нового отеля с пустыми полями ввода.

Также присутствует кнопка **"Посмотреть статистику"**, по клику на которую открывается форма с диаграммой распределения комментариев по отелям.

Кроме того, в шапке формы есть кнопка **"Войти"** для авторизации.

###Форма просмотра подробной информации об отеле###

Форма представляет из себя шапку, аналогичную шапке формы просмотра списка отелей и 2 панели - информация об отеле и комментарии.

В панели **информации об отеле** выводится только одно дополнительное поле, которе не присутствует в форма просмотра списка отелей, - "Описание", оно содержит общую информацию об отеле. Здесь же представлены кнопки **"Забронировать отель"** (доступна всем), **"Редактировать"** и **"Удалить"** (последние 2 доступны только администратору). По клику на кнопку **"Забронировать отель"** открывается модальный диалог с просьбой предоставить контактные данные (на самом деле он ничего не делает). По клику на кнопку **"Редактировать"** открывается форма редактирования отеля, поля ввода которой заполнены текущими данными об отеле. По кнопке **"Удалить"** отель удаляется из базы и просизодит переход на форму просмотра списка отелей. 

В панели **комментариев** выводится список комментариев к данному отелю с указанием автора и ссылки для удаления комментария, а также форма добавления комментариев. Доступность элементов этой панели зависит от роли пользователя:
 - Администратор - доступны ссылки для удаления любого комментария;
 - Пользователь - доступны ссылки для удаления своих комментариев (проверка имя пользователя == автор комментария);
 - Анонимный пользователь - недоступны ссылки для удаления комментариев.

Если пользователь авторизован (т.е. не анонимный), то поле **"Автор"** на форме добавления комментария автоматически заполниется именем пользователя и недоступно для редактирования, иначе - поле пустое и доступно для редактирования. Оба поля на форме добавления комментария являются обязательными.

###Форма добавления/редактирования отеля###

Формы представляют из себя список полей ввода - "Название", "Адрес", "Телефон", "Цена за ночь", "Описание". Все поля, кроме "Описания", являются обязательными. На поле "Цена за ночь" наложения дополнительная валидация - туда можно ввести только цифры.

Также на формах присутствуют кнопки для сохранения и отмены изменений.
