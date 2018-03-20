package api

import model.Character
import model.Page
import kotlin.js.Promise

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
