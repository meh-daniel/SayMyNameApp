package meh.daniel.com.saymynameapp.data.db.modelSW

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = CharacterSW.TABLE_NAME,
    indices = [
        Index("name", unique = true )
    ]
)
data class CharacterSW(
    @PrimaryKey
    @NotNull
    val id: Long,
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    @NotNull
    val name: String,
    val image: String,
    val birthday: String,
) {
    companion object {
        const val TABLE_NAME = "character"
    }
}