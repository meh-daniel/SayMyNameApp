package meh.daniel.com.hero_component.domain

interface HeroRepository {
    suspend fun getAll(): List<Hero>
    suspend fun getById(id: Int): Hero
    suspend fun searchByName(name: String): List<Hero>
}