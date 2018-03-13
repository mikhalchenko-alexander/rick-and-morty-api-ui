package model

@Suppress("UnsafeCastFromDynamic")
class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationShort,
    val location: LocationShort,
    val image: String,
    val episode: Array<String>,
    val url: String,
    val created: String
) {

    companion object {

        fun parse(json: dynamic): Character =
            Character(
                id = json.id,
                name = json.name,
                status = json.status,
                species = json.species,
                type = json.type,
                gender = json.gender,
                origin = Location.parse(json.origin),
                location = Location.parse(json.location),
                image = json.image,
                episode = json.episode,
                url = json.url,
                created = json.created
            )

    }

}
