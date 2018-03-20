package components.characterpage.charactersearch

import components.searchbar.SearchFilter
import components.searchbar.searchBar
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

interface CharacterSearchProps : RProps {
    var additionalClasses: Set<String>
    var onSearch: (CharacterSearchFilter) -> Unit
}

data class CharacterSearchFilter(
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val gender: String = "",
    val type: String = ""
) : SearchFilter

class CharacterSearch : RComponent<CharacterSearchProps, RState>() {

    override fun RBuilder.render() {
        div(classes = "CharacterPage__CharacterSearch") {
            attrs {
                classes += props.additionalClasses
            }

            searchBar(
                CharacterSearchFilter(),
                mapOf(
                    "Name" to { value -> this.copy(name = value) },
                    "Status" to { value -> this.copy(status = value) },
                    "Species" to { value -> this.copy(species = value) },
                    "Gender" to { value -> this.copy(gender = value) },
                    "Type" to { value -> this.copy(type = value) }
                )) { props.onSearch(it) }
        }
    }

}

fun RBuilder.characterSearch(additionalClasses: Set<String>, onSearch: (CharacterSearchFilter) -> Unit) = child(CharacterSearch::class) {
    attrs.additionalClasses = additionalClasses
    attrs.onSearch = onSearch
}
