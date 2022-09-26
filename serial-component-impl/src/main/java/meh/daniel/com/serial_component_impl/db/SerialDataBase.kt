package meh.daniel.com.serial_component_impl.db

import androidx.room.Database
import androidx.room.RoomDatabase
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterDetailsSW
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSwWithCharacterSW

@Database(
    version = 3,
    entities = [
        CharacterDetailsSW::class,
        CharacterSW::class,
        EpisodeSW::class,
        EpisodeSwWithCharacterSW::class,
    ]
)
abstract class SerialDataBase: RoomDatabase() {
    abstract fun serialDao(): SerialDao
}