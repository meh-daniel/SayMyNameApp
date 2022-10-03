# SayMyNameApp
Приложение показывает список персонажей из сериал "Во все тяжкие"

Api использованное в приложение:
https://breakingbadapi.com/

Дизайн приложения
=====================

* Presentation
    * SingleActivity
    * ModelUI
    * Screens
        * Fragments
        * ViewModels
        * Adapters
        * ViewStates
        * ViewActions
* Domain
    * SerialRepository
    * Model
* Data
    * Mappers
    * SerialRepositoryImpl
    * NW
        * BreakingBadApi
        * ModelNW
    * DB
        * SerialDao
        * SerialDataBase
        * ModelSW
* Di
    * NetworkModule
    * SerialComponentModule
* Core
    * BaseFragment
    * BaseViewModel
* App


| | | |
|:-------------------------:|:-------------------------:|:-------------------------:|
|<img width="1604"  src="https://github.com/meh-daniel/SayMyNameApp/blob/dev/photo-readme/SayMyName1.jpg"> |  <img width="1604" src="https://github.com/meh-daniel/SayMyNameApp/blob/dev/photo-readme/SayMyName2.jpg">|<img width="1604" src="https://github.com/meh-daniel/SayMyNameApp/blob/dev/photo-readme/SayMyName3.jpg">|

### Use case:
+ Просмотр cписка персонажей по эпизодам сериала breakingBad
+ Просмотр детальной информации о персонаже
+ Поиск персонажа по имени



### применённые библиотеки и фреймворки:
+ BuildGradleDSL
+ Hilt
+ Lifecycle
+ Room
+ Retrofit
+ okhttp3
+ converter gson
+ jetpack navigation
+ Coroutines
