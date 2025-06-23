// Database schema for the Music Club app

import org.jetbrains.exposed.dao.id.UIntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

const val MAX_LENGTH = 300

object Artists: UIntIdTable() {
    val name = varchar("name", MAX_LENGTH)
    val isSolo = bool("is_solo").default(false)
    val info = varchar("info_url", MAX_LENGTH).nullable()
}

object Albums: UIntIdTable() {
    val artist = reference("artist_id", Artists, ReferenceOption.CASCADE)
    val title = varchar("title", MAX_LENGTH)
    val year = integer("year")
    val youtube = varchar("youtube_url", MAX_LENGTH).nullable()
}
