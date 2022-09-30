package meh.daniel.com.saymynameapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import meh.daniel.com.saymynameapp.data.db.modelSW.CharacterDetailsSW
import meh.daniel.com.saymynameapp.data.db.modelSW.CharacterSW
import meh.daniel.com.saymynameapp.data.db.modelSW.EpisodeSW

@Dao
interface SerialDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: List<CharacterSW>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterDetails(characterDetailsSW: CharacterDetailsSW)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episodeSW: EpisodeSW)

    @Query("SELECT * FROM character WHERE name LIKE :name")
    suspend fun findCharacterByName(name: String) : List<CharacterSW>

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacterById(id: Long) : CharacterSW

    @Query("SELECT * FROM character_details WHERE id = :id")
    suspend fun getCharacterDetails(id: Long) : CharacterDetailsSW

    @Query("SELECT * FROM episode WHERE number_episode = :episode")
    suspend fun getEpisode(episode: Long) : EpisodeSW

}