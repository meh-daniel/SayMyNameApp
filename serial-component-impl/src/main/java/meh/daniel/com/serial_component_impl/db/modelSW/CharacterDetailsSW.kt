package meh.daniel.com.serial_component_impl.db.modelSW

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "character_details"
)
data class CharacterDetailsSW(
    @PrimaryKey
    val id: Long,
    val name: String,
    val birthday: String,
    val image: String,
    val status: String,
    val nickname: String,
    val portrayed: String,
)