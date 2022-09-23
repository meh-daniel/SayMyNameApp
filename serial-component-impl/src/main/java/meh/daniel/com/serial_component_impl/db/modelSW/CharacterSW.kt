package meh.daniel.com.serial_component_impl.db.modelSW

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "character",
    indices = [
        Index("name", unique = true )
    ]
)
data class CharacterSW(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val name: String,
    val image: String,
    val birthday: String,
)