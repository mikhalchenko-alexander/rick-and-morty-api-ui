package model

@Suppress("UnsafeCastFromDynamic")
class Page<out T>(
    val info: Info,
    val results: List<T>
) {

    companion object {

        fun parseLocations(json: dynamic): Page<Location> =
            Page(
                info = Info.parse(json.info),
                results = (json.results as Array<*>).map(Location.Companion::parse)
            )

        fun parseCharacters(json: dynamic): Page<Character> =
            Page(
                info = Info.parse(json.info),
                results = (json.results as Array<*>).map(Character.Companion::parse)
            )

        fun parseEpisodes(json: dynamic): Page<Episode> =
            Page(
                info = Info.parse(json.info),
                results = (json.results as Array<*>).map(Episode.Companion::parse)
            )

    }

}

@Suppress("UnsafeCastFromDynamic")
class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val previous: String
) {

    companion object {

        fun parse(json: dynamic): Info =
            Info(
                count = json.count,
                pages = json.pages,
                next = json.next,
                previous = json.prev
            )

    }

}
