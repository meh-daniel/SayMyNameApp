package meh.daniel.com.serial_component_impl.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterDetailsSW
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSwWithCharacterSW

@Dao
interface SerialDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCharacter(vararg character: CharacterSW) : List<Long>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCharacterDetails( characterDetailsSW: CharacterDetailsSW) : List<Long>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertEpisode( episodeSW: EpisodeSW): List<Long>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertEpisodeWithCharacter(episodeWithCharacter: EpisodeSwWithCharacterSW): Long
//
//    @Query("SELECT * FROM character WHERE name = :name")
//    suspend fun findCharacterByName(name: String) : List<CharacterSW>
//
//    @Query("SELECT * FROM character_details WHERE id = :id")
//    suspend fun getCharacterDetails(id: Long) : CharacterDetailsSW
//
//    @Query("SELECT * FROM character LEFT JOIN episode_with_character ON character.id = episode_id AND character_id = :number")
//    fun getEpisode(number: Long) : Flow<Map<EpisodeSW, EpisodeSwWithCharacterSW>>

}