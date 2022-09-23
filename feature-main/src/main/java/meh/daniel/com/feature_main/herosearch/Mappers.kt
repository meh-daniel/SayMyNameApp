package meh.daniel.com.feature_main.herosearch

internal fun List<meh.daniel.com.serial_component.model.Hero>.toUI(): List<HeroUI> {
    return map {
        HeroUI.Hero(
            id = it.id,
            name = it.name,
            image = it.image,
            birthdate = it.birthday,
        )
    }
}