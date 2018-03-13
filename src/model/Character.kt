package model

interface Character {
    val id: Int
    val name: String
    val status: String
    val species: String
    val type: String
    val gender: String
    val origin: LocationShort
    val location: LocationShort
    val image: String
    val episode: Array<String>
    val url: String
    val created: String
}
