package meh.daniel.com.serial_component_impl.db

import androidx.room.Database
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterDetailsSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSwWithCharacterSW

@Database(
    version = 1,
    entities = [
        CharacterDetailsSW::class,
        CharacterDetailsSW::class,
        EpisodeSW::class,
        EpisodeSwWithCharacterSW::class,
    ]
)
abstract class SerialDataBase {
    abstract fun serialDao(): SerialDao
}