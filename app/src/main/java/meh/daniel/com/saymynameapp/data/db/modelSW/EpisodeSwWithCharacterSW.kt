package meh.daniel.com.saymynameapp.data.db.modelSW

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = EpisodeSwWithCharacterSW.TABLE_NAME,
    primaryKeys = [
        "episode_id",
        "character_id",
    ],
    indices = [
        Index("character_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = EpisodeSW::class,
            parentColumns = ["id"],
            childColumns = ["episode_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = CharacterSW::class,
            parentColumns = ["id"],
            childColumns = ["character_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ],
)
data class EpisodeSwWithCharacterSW(
    @ColumnInfo(name = "episode_id")
    @NotNull
    val episodeId: Long,
    @ColumnInfo(name = "character_id")
    @NotNull
    val characterId: Long,
)  {
    companion object {
        const val TABLE_NAME = "episode_with_character"
    }
}