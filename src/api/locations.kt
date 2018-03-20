package api

import model.Location
import model.Page
import kotlin.js.Promise

fun getLocations(
    name: String = "",
    type: String = "",
    dimension: String = "",
    url: String = "$baseUrl/location?name=$name&type=$type&dimension=$dimension"): Promise<Page<Location>> {
    return getAndParse(url)
}
