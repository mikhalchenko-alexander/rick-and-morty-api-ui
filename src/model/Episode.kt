package model

interface Episode {
    val id: Int
    val name: String
    @JsName("air_date")
    val airDate: String
    val episode: String
    val characters: Array<String>
    val url: String
    val created: String
}
