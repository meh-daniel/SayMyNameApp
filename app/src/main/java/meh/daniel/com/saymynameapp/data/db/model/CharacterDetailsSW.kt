package meh.daniel.com.saymynameapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = CharacterDetailsSW.TABLE_NAME
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
) {
    companion object {
        const val TABLE_NAME = "character_details"
    }
}