<#-- TODO: fix bugs with old images and time numbers < 10 -->

<#include "../components/header.ftlh">

<h3>Подробная информация о блюде(id=${dish.id}, название=${dish.name})</h3>

<hr>

<#if dish??>
    <b>Id блюда: </b>${dish.id}<br>
    <b>Название блюда: </b>${dish.name}<br>
    <b>Описание блюда: </b>${dish.description}<br>
    <b>Цена блюда: </b>${dish.price} руб.<br>
    <b>Время приготовления
        блюда: </b>${dish.getCookingTime().toHoursPart()} ч / ${dish.getCookingTime().toMinutesPart()} мин / ${dish.getCookingTime().toSecondsPart()} с
    <br>
    <b>Количество блюд: </b>${dish.count} штук<br>
    <b>Вес блюда: </b>${dish.weight} (в граммах)<br>
    <b>Статус активности блюда (в актуальном меню/в разработке): </b>${dish.active?c}<br>
    <#if dish.getImages()?has_content>
        <b>Изображения блюда: </b><br>
        <ul>
            <#list dish.getImages() as image>
                <li><img src="/image/${image.id}" height="60px" alt="img"></li>
            </#list>
        </ul>
    <#else>
        <b>Изображений блюда нет.</b><br>
    </#if>

    <hr>

    <h3>Обновить информацию о блюде</h3>
    <form action="/admin/dish/update/${dish.id}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        Название блюда: <input type="text" name="name" value="${dish.name}"/><br>
        Описание блюда: <input type="text" name="description" value="${dish.description}"/><br>
        Цена блюда (в руб): <input type="number" min="0" max="9223372036854775807" step="1" name="price" value="${dish.price}"
                           title="Цена должна быть целым неотрицательным числом, меньшим 9 223 372 036 854 775 807."/><br>
        Время приготовления блюда: <input type="text" pattern="${regexTime}" name="cookingTimeStr"
                                          placeholder="HH:mm:ss"
                                          required title="Введите время приготовления в формате HH:mm:ss с нулями"
                                          value="${dish.cookingTime.toHoursPart()}:${dish.cookingTime.toMinutesPart()}:${dish.cookingTime.toSecondsPart()}"/><br>
        Количество блюда (в штуках): <input type="number" name="count" min="0" max="9223372036854775807" step="1"
                                 value="${dish.count}"
                                 title="Количество должно быть целым неотрицательным числом, меньшим 9 223 372 036 854 775 807."/><br>
        Вес блюда (в граммах): <input type="number" name="weight" min="0" max="2147483647" step="1" value="${dish.weight}"
                          title="Цена должна быть целым неотрицательным числом, меньшим 2 147 483 647."><br>
        Статус активности блюда (в актуальном меню/в разработке): <input type="checkbox" name="active" value="true" ${dish.active?string('checked', '')}/><br>
        <#-- TODO: limit neither count of files neither MB size -->
        Изображения блюда (оставьте пустым, если хотите оставить старые изображения): <input type="file" name="files" multiple accept=".png, .jpg, .jpeg, .gif"/><br>

        <input type="submit" value="обновить блюдо"/>
    </form>

<#else>
    <h4>Блюдо с таким id не существует.</h4>
    <hr>
</#if>

<#include "../components/footer.ftlh">
