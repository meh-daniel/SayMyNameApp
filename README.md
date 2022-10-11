# SayMyNameApp
The application shows a list of characters from the series "Breaking Bad"

API used in the application:
https://breakingbadapi.com/

# Application design

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
+ View the list of characters by episodes of the series breakingBad
+ View detailed information about the character
+ Search character by name


# Applied libraries and frameworks:
https://github.com/meh-daniel/SayMyNameApp/blob/master/buildSrc/src/main/kotlin/Deps.kt
