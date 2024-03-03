# Конструирование Программного Обеспечения: Домашняя работа №2

## Правила бойцовского клуба
1. Не использовать значения по умолчанию. Явная инициализация всех полей.

## Условие

Разработать систему управления заказами в ресторане, которая поддерживает два типа пользователей: посетителей и
администраторов. Система должна обрабатывать заказы в многопоточном режиме, позволяя клиентам добавлять блюда в заказ в
реальном времени, а также отображать статусы заказов. Администраторы могут управлять меню, добавляя или удаляя блюда.
Все решения должны соблюдать принципы ООП и SOLID, а также использовать шаблоны проектирования, где это уместно.

## Требования

- [ ] Реализуйте систему аутентификации для двух типов пользователей: посетителей и администраторов.
- [ ] Администратор может добавлять и удалять блюда из меню, а также устанавливать их количество, цену и сложность
  выполнения (время, которое оно будет готовиться).
- [ ] Посетители могут составлять заказ, выбирая блюда из актуального меню.
- [ ] Заказы обрабатываются в отдельных потоках, симулируя процесс приготовления.
- [ ] Посетители могут добавлять блюда в существующий заказ, пока он находится в обработке.
- [ ] Посетители должны иметь возможность отменять заказ до того, как он будет готов.
- [ ] Система должна отображать актуальный статус заказа (например, "принят", "готовится", "готов").
- [ ] По завершению выполнения заказа посетитель должен иметь возможность его оплатить.
- [ ] Необходимо сохранять состояния программы: меню, сумму выручки, пользователей в системе, а также то, что вы
  посчитаете нужным.

## Критерии оценки

- [ ] Применение принципов ООП и SOLID (0 – 1 балл)
- [ ] Аутентификация (0 – 1 балл)
- [ ] Использование шаблонов проектирования (0 – 1 балл)
- [ ] Хранение данных (0 – 1 балл)
- [ ] Реализация многопоточности для обработки заказов (0 – 2 балла)
- [ ] Codestyle (0 – 1 балл)
- [ ] readme с описанием того, как пользоваться программой, какие шаблоны были использованы в проекте и почему (0 – 0.5
  балла)
- [ ] Понятный интерфейс (0 – 0.5 балла)

## Опциональное задание (на дополнительные 1 – 2 балла)

- [ ] Дать возможность клиентам оставлять отзывы о блюдах после оплаты заказа. Отзывы должны включать оценку от 1 до 5 и
  текстовый комментарий.
- [ ] Реализовать функционал, позволяющий администратору просматривать статистику по заказам и отзывам (например, самые
  популярные блюда, средняя оценка блюд, количество заказов за период).
- [ ] Система приоритетов для обработки заказов (вы должны сами решить, по какому принципу приоритезированы заказы,
  объяснив своё решение в readme).

## Дополнительно

- Штраф –3 балла за наличие ошибки во время выполнения кода
- Оценка снижается до 0, если программа не собирается (должен быть предоставлен полный код программы)
- Код должен быть загружен на удаленный Git-репозиторий с четкой структурой каталогов и созданным pull/merge request из
  ветки develop в пустую ветку main.
- Предусмотрено снижение оценки пропорционально объему невыполненных функциональных требований.

## Дополнительная информация

- Выполнять ДЗ можно на языках Kotlin или Java.
- Дедлайн сдачи: 10 марта, 23:59.

## Зависимости

```
Lombok

Apache Freemaker
Thymeleaf

Java Mail Sender
JDBC API

mapstruct

Spring Data JPA
Spring Security
Spring Web

PostgreSQL Driver
```

## Информация

Пользователь БД: `postgres`
Пароль от БД: `postgres`

## Команды

```
docker-compose up -d

```

## Структура проекта

```
| src
|-- main
|---- java
|------ com.RestaurantManagementSystem
|-------- controlles <- "ручки" приложения.
|-------- dto <- Data Transfer Object, отображения БД.
|-------- models <- модели БД (Entity).
|-------- repositories <- .
|-------- services <- CRUD-операции.
|-- test
```

## Логика создания заказа

У заказа есть только три состояния:

- `Готовится`;
- `Готов`;
- `Отменён`.

```
Надо работать с БД асинхронно и потокобезопасно... :)
```

- При создании заказа в БД `orders` добавляется запись:

| id                 | cooking_dishes                                               | cooked_dishes | cost                      | start_time    | end_time | status      |
|--------------------|--------------------------------------------------------------|---------------|---------------------------|---------------|----------|-------------|
| Сгенерированный Id | Все заказанные блюда (ВАЖНО: могут быть повторяющиеся блюда) | `NULL`        | стоимость заказанных блюд | текущее время | `NULL`   | `Готовится` |

- При добавлении блюда в заказ в БД `orders` обновляется запись:

| id        | cooking_dishes                                                            | cooked_dishes         | cost                                           | start_time          | end_time | status      |
|-----------|---------------------------------------------------------------------------|-----------------------|------------------------------------------------|---------------------|----------|-------------|
| Id заказа | Добавляется id добавленного блюда (ВАЖНО: могут быть повторяющиеся блюда) | \*Текущее состояние\* | старая стоимость + стоимость заказанного блюда | Время начала заказа | `NULL`   | `Готовится` |

- Когда какое-то блюдо готово, обновляется запись в БД `orders`:

| id        | cooking_dishes                                                           | cooked_dishes             | cost                       | start_time   | end_time | status      |
|-----------|--------------------------------------------------------------------------|---------------------------|----------------------------|--------------|----------|-------------|
| Id заказа | Удаляется одно id готового блюда (ВАЖНО: могут быть повторяющиеся блюда) | Добавляется готовое блюдо | Старое время начала заказа | старое время | `NULL`   | `Готовится` |

- Когда все блюда готовы (пустой cooking_dishes), обновляется запись в БД `orders`, проверка после каждого
  приготовленного блюда:

| id        | cooking_dishes             | cooked_dishes    | cost                  | start_time                 | end_time          | status                     |
|-----------|----------------------------|------------------|-----------------------|----------------------------|-------------------|----------------------------|
| Id заказа | \*Здесь становится пусто\* | Все блюда готовы | стоимость не меняется | Старое время начала заказа | Обновляется время | Меняется статус на `Готов` |

- Когда заказ отменяют, обновляется запись в БД `orders`:

| id                 | cooking_dishes        | cooked_dishes         | cost                      | start_time                 | end_time          | status                       |
|--------------------|-----------------------|-----------------------|---------------------------|----------------------------|-------------------|------------------------------|
| Сгенерированный Id | \*Текущее состояние\* | \*Текущее состояние\* | стоимость заказанных блюд | Старое время начала заказа | Обновляется время | Меняется статус на `Отменён` |

## Задачи

- [ ] Привести к норм кодстайлу;
- Алфавитный порядок декораторов?
- [ ] Поставить нормальные ошибки;
- [ ] Допилить dto + mappers;

- [ ] Makefile;
- [ ] .env;
- [ ] JWT авторизация?;
- [ ] Разобраться с .gitignore + удалить лишние файлы;
- [ ] Сделать асинхронную БД?;

- [ ] В Orders хранятся выполненные заказы. Заказы входят по принципу Stack'а FIFO.
- [ ] Добавить логику для сжатия файлов картинок.
- [ ] Разобраться с FetchType.EAGER и FetchType.LAZY.
- [ ] Проставить nullable = false, убрать nullable = true.

## Дополнительные задачи

- [ ] Сделать возможность смены почты и пароля;
- [ ] Сделать проверку по почте;
- [ ] Многоязычность;

## Использованные шаблоны проектирования

1. DTO (Data Transfer Object);
2. Repository;
3. 