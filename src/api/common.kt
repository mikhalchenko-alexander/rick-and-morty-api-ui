package api

import org.w3c.fetch.*
import kotlin.browser.window
import kotlin.js.Promise

const val baseUrl = "https://rickandmortyapi.com/api"

@Suppress("UNCHECKED_CAST")
fun <T> getAndParse(url: String): Promise<T> {
    return window.fetch(url, RequestInit(
        method = "GET",
        headers = Headers().apply {
            append("Accept", "application/json")
        },
        mode = org.w3c.fetch.RequestMode.CORS,
        cache = org.w3c.fetch.RequestCache.NO_CACHE,
        redirect = org.w3c.fetch.RequestRedirect.FOLLOW,
        integrity = "",
        referrerPolicy = "no-referrer",
        credentials = org.w3c.fetch.RequestCredentials.OMIT
    )).then(Response::json).then { it as T }
}
