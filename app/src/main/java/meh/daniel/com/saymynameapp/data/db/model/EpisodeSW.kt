package meh.daniel.com.saymynameapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = EpisodeSW.TABLE_NAME,
    indices = [
        Index("name", unique = true )
    ]
)
data class EpisodeSW(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    @NotNull
    val name: String,
    @ColumnInfo(name = "number_episode")
    val numberEpisode: Int,
    @ColumnInfo(name = "list_id_character")
    val listIdCharacter: String
) {
    companion object {
        const val TABLE_NAME = "episode"
    }
}