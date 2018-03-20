package api

import model.Episode
import model.Page
import kotlin.js.Promise

fun getEpisode(id: Int): Promise<Episode> {
    return getAndParse("$baseUrl/episode/$id")
}

fun getEpisodes(
    name: String = "",
    episode: String = "",
    url: String = "$baseUrl/episode?name=$name&episode=$episode"): Promise<Page<Episode>> {
    return getAndParse(url)
}

fun getEpisodes(episodeUrls: List<String>): Promise<List<Episode>> {
    val episodePromises = episodeUrls.map { episodeUrl ->
        val id = episodeUrl.takeLastWhile { it != '/' }.toInt()
        getEpisode(id)
    }.toTypedArray()
    return Promise.all(episodePromises).then(Array<out Episode>::toList)
}
