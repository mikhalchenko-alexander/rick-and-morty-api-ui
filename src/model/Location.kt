package model

interface LocationShort {
    val name: String
    val url: String
}

@Suppress("UnsafeCastFromDynamic")
class Location(
    val id: Int,
    override val name: String,
    val type: String,
    val dimension: String,
    val residents: Array<String>,
    override val url: String,
    val created: String
) : LocationShort {

    companion object {

        fun parse(json: dynamic): Location =
            Location(
                id = json.id,
                name = json.name,
                type = json.type,
                dimension = json.dimension,
                residents = json.residents,
                url = json.url,
                created = json.created
            )

    }

}
