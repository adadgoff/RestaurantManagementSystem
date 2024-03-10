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
    <#if dish.getImages()?has_content>
        <b>Изображения блюда: </b><br>
        <ul>
            <#list dish.getImages() as image>
                <li><img src="/image/${image.id}" height="100px" alt="img"></li>
            </#list>
        </ul>
    <#else>
        <b>Изображений блюда нет.</b><br>
    </#if>

<#--  TODO: reviews  -->

<#else>
    <h3>Блюдо с таким id не существует.</h3>
    <hr>
</#if>

<#include "../components/footer.ftlh">
