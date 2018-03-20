package api

import model.Character
import model.Episode
import model.Location
import model.Page
import org.w3c.fetch.*
import kotlin.browser.window
import kotlin.js.Promise

private const val baseUrl = "https://rickandmortyapi.com/api"

fun getCharacters(
    name: String = "",
    status: String = "",
    species: String = "",
    gender: String = "",
    type: String = "",
    url: String = "$baseUrl/character?name=$name&status=$status&species=$species&gender=$gender&type=$type"): Promise<Page<Character>> {
    return getAndParse(url)
}

fun getCharacter(id: Int): Promise<Character> {
    return getAndParse("$baseUrl/character/$id")
}

fun getCharacters(characterUrls: List<String>): Promise<List<Character>> {
    val characterPromises = characterUrls.map { characterUrl ->
        val id = characterUrl.takeLastWhile { it != '/' }.toInt()
        getCharacter(id)
    }.toTypedArray()
    return Promise.all(characterPromises).then(Array<out Character>::toList)
}

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

fun getLocations(
    name: String = "",
    type: String = "",
    dimension: String = "",
    url: String = "$baseUrl/location?name=$name&type=$type&dimension=$dimension"): Promise<Page<Location>> {
    return getAndParse(url)
}

@Suppress("UNCHECKED_CAST")
private fun <T> getAndParse(url: String): Promise<T> {
    return window.fetch(url, RequestInit(
        method = "GET",
        headers = Headers().apply {
            append("Accept", "application/json")
        },
        mode = RequestMode.CORS,
        cache = org.w3c.fetch.RequestCache.NO_CACHE,
        redirect = org.w3c.fetch.RequestRedirect.FOLLOW,
        integrity = "",
        referrerPolicy = "no-referrer",
        credentials = RequestCredentials.OMIT
    )).then(Response::json).then { it as T }
}
