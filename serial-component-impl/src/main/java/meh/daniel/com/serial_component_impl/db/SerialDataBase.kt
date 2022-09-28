package meh.daniel.com.serial_component_impl.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterDetailsSW
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSwWithCharacterSW

@Database(
    entities = arrayOf(
        CharacterDetailsSW::class,
        CharacterSW::class,
        EpisodeSW::class,
        EpisodeSwWithCharacterSW::class,
    ),
    version = SerialDataBase.DB_VERSION,
    exportSchema = false,
)
abstract class SerialDataBase: RoomDatabase() {
    companion object {
        const val DB_VERSION = 6
        private const val DB_NAME = "serial.db"

        @Volatile private var instance : SerialDataBase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SerialDataBase::class.java,
            DB_NAME
        ).build()
    }
    abstract fun serialDao(): SerialDao
}