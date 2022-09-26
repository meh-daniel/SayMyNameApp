package meh.daniel.com.serial_component_impl.db.modelSW

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "character_details"
)
data class CharacterDetailsSW(
    @PrimaryKey
    @NotNull
    val id: Long,
    val name: String,
    val birthday: String,
    val image: String,
    val status: String,
    val nickname: String,
    val portrayed: String,
)