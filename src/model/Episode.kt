package model

@Suppress("UnsafeCastFromDynamic")
class Episode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: Array<String>,
    val url: String,
    val created: String
) {

    companion object {

        fun parse(json: dynamic): Episode =
            Episode(
                id = json.id,
                name = json.name,
                airDate = json.air_date,
                episode = json.episode,
                characters = json.characters,
                url = json.url,
                created = json.created
            )

    }

}
