package model

interface LocationShort {
    val name: String
    val url: String
}

interface Location: LocationShort {
    val id: Int
    val type: String
    val dimension: String
    val residents: Array<String>
    val created: String
}
