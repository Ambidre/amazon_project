# Проект по автоматизации тестирования UI для веб-приложения [Amazon](https://www.amazon.com/)

## Покрытый функционал
> Разработаны автотесты на <code>UI</code>.
- :white_check_mark: Првоерка отображения верного тайтла
- :white_check_mark: Проверка отображения информации в футере
- :white_check_mark: Проверка отображения пунктов меню в навигационной панели у неавторизованного пользователя
- :white_check_mark: Проверка отображения искомых товаров на странице результатов поиска
- :white_check_mark: Проверка отображения категорий в фильтре "Категория" на странице поиска товаров
- :white_check_mark: Проверка отображения категорий в блоке обзора по категориям на основной странице категории товара
- :white_check_mark: Отсутствие ошибок в журнале консоли страницы

## Технологический стек

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Allure TestOps" src="images/logo/Allure_TestOps.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
<img width="6%" title="Jira" src="images/logo/Jira.svg">
</p>

> В данном проекте автотесты написаны на <code>Java</code> с использованием фреймворков <code>Selenide</code> для UI-тестов.
>
> <code>Selenoid</code> выполняет запуск приложения на удаленном сервере в контейнерах <code>Docker</code>.
>
> <code>JUnit 5</code> используется для модульного тестирования
>
> <code>Gradle</code> используется для автоматизированной сборки проекта
>
> <code>Jenkins</code> выполняет запуск тестов
>
> <code>Allure Report</code> формирует отчет о запуске тестов
>
> Автотесты интегрируются с тест-менеджмент системой <code>Allure TestOps</code> и таск-трекер системой <code>Jira</code>
>
> В <code>Telegram</code> отправляются уведомления о пройденном прогоне с помощью библиотеки [allure-notifications](https://github.com/qa-guru/allure-notifications)

## Запуск тестов из терминала
### Локальный запуск тестов

```
gradle clean test
```

### Удаленный запуск тестов с заполненным файлом _credentials.properties_

```
gradle clean test
-DremoteURL=${REMOTEURL}
-Dbrowser=${BROWSER}
-DversionBrowser=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-Dthreads=${THREADS}
```
> <details>
> <summary> :point_left: <code>credentials.properties</code> </summary>
>
> **В файле должны быть расположены:**
> + <code>login</code> и <code>password</code> – данные для авторизации на удаленном сервере, на котором будут запускаться тесты
> </details>


### Параметры сборки

> <code>REMOTE_URL</code> – адрес удаленного сервера, на котором будут запускаться тесты.
>
> <code>BROWSER</code> – браузер, в котором будут выполняться тесты (_по умолчанию - <code>chrome</code>_).
>
> <code>BROWSER_VERSION</code> – версия браузера, в которой будут выполняться тесты (_по умолчанию - <code>91.0</code>_).
>
> <code>BROWSER_SIZE</code> – размер окна браузера, в котором будут выполняться тесты (_по умолчанию - <code>1920x1080</code>_).
>
> <code>THREADS</code> – количество потоков для запуска тестов.
## <img width="4%" title="Jenkins" src="images/logo/Jenkins.svg"> Для запусков автотестов используется [Jenkins](https://jenkins.autotests.cloud/job/09-Ambidre-mobile_tests/)

![Jenkins](images/screens/jenkins.png)

## <img width="4%" title="Allure Report" src="images/logo/Allure_Report.svg"> Анализ результатов запусков в Jenkins через Allure Reports

> <code>Allure-framework</code>используется в качестве инструмента для построения отчетов о прогоне автотестов.
> Он позволяет получить информацию о ходе выполнения тестов, а также прикрепить скриншоты, логи и видео к формируемому отчету.
> Имеется возможность указать различные теги, приоритеты и прочую сопутствующую информацию для тестов.

### Главная страница Allure-отчета

![Jenkins_Allure_Reports](images/screens/allure_dashbord.png)

### Информация о тестовом прогоне в графическом виде

![Jenkins_Allure_Reports](images/screens/allure_graphs.png)

### Группировка тестов по проверяемому функционалу

![Jenkins_Allure_Reports](images/screens/allure_detailes.png)

## <img width="4%" title="Allure TestOps" src="images/logo/Allure_TestOps.svg"> Интеграция тестов c тест-менеджмент системой [Allure TestOps](https://allure.autotests.cloud/jobrun/9938)

> <code>Allure TestOps</code> используется для хранения всех авто и ручных тестов, запусков и их результатов, а также статистики и отчетов.

### Основной дашборд

![Allure Overview Dashboard](images/screens/allure_overview_dashboard.png)

### Дашборд для отображения успешности и длительности тестов

![Allure Overview Dashboard](images/screens/allure_duration_and_success_rate_dashboard.png)

### Дашборд по стендам

![Allure Overview Dashboard](images/screens/allure_stands_dashboard.png)

### Дашборд по членам команды

![Allure Overview Dashboard](images/screens/allure_team_dashboard.png)

### Запуски тестов

![Allure Overview Dashboard](images/screens/allure_launches.png)

### Результаты запуска тестов

![Allure_Launches](images/screens/testops_detailes.png)

### Сгруппированные тест-кейсы по проверяемому функционалу

![Allure TestOps](images/screens/allure_testcases.png)

## <img width="4%" title="Jira" src="images/logo/Jira.svg"> Интеграция тестов c таск-трекер системой [Jira](https://jira.autotests.cloud/browse/HOMEWORK-336)

> Интеграция с <code>Jira</code> позволяет добавлять в задачи тест-кейсы, запуски и их результаты.

![Jira Issues](images/screens/jira_issues.png)

## <img width="4%" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

> Реализована отправка уведомлений о прогоне с помощью бота в <code>Telegram</code>.

![Telegram Notifications](images/screens/telegram_notifications.png)

## <img width="4%" title="Selenoid" src="images/logo/Selenoid.svg"> Пример запуска теста в Selenoid

> К каждому тесту в отчете прилагается видео. Одно из таких видео представлено ниже.

![Selenoid Video](images/gif/selenoid_video.gif)
