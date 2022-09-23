package meh.daniel.com.serial_component_impl.db.modelSW

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "episode",
    indices = [
        Index("name", unique = true )
    ]
)
data class EpisodeSW(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val name: String,
    @ColumnInfo(name = "number_episode")
    val numberEpisode: Int,
    val character: List<CharacterSW>,
)