package meh.daniel.com.serial_component_impl.db.modelSW

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "episode_with_character",
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
    @ColumnInfo(name = "episode_id") val episodeId: Long,
    @ColumnInfo(name = "character_id") val characterId: Long,
)