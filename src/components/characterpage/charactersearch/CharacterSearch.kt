package components.characterpage.charactersearch

import components.button.buttonCustom
import kotlinx.html.classes
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onKeyPressFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div
import react.dom.input
import react.dom.label

interface CharacterSearchProps : RProps {
    var additionalClasses: Set<String>
    var onSearch: (CharacterSearchFilter) -> Unit
}

interface CharacterSearchState : RState {
    var characterSearchFilter: CharacterSearchFilter
}

data class CharacterSearchFilter(
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val gender: String = "",
    val type: String = ""
)

class CharacterSearch : RComponent<CharacterSearchProps, CharacterSearchState>() {

    override fun CharacterSearchState.init() {
        characterSearchFilter = CharacterSearchFilter()
    }

    override fun RBuilder.render() {
        div(classes = "CharacterSearch") {
            attrs {
                classes += props.additionalClasses
            }

            searchInput("Name", { this.copy(name = it) })
            searchInput("Status", { this.copy(status = it) })
            searchInput("Species", { this.copy(species = it) })
            searchInput("Gender", { this.copy(gender = it) })
            searchInput("Type", { this.copy(type = it) })

            buttonCustom("Search!", setOf("CharacterSearch__Button"), { props.onSearch(state.characterSearchFilter) })
        }
    }

    private fun RBuilder.searchInput(label: String, stateUpdater: CharacterSearchFilter.(String) -> CharacterSearchFilter) {
        val id = label.toLowerCase().replace(" ", "-")
        label {
            attrs {
                set("htmlFor", id)
            }
            +"$label:"
        }
        input(classes = "CharacterSearch__SearchInput") {
            attrs {
                this@attrs.id = id
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    setState {
                        characterSearchFilter = characterSearchFilter.stateUpdater(target.value)
                    }
                }
                onKeyPressFunction = {
                    if (it.asDynamic().key == "Enter") {
                        props.onSearch(state.characterSearchFilter)
                    }
                }
            }
        }
    }

}

fun RBuilder.characterSearch(additionalClasses: Set<String>, onSearch: (CharacterSearchFilter) -> Unit) = child(CharacterSearch::class) {
    attrs.additionalClasses = additionalClasses
    attrs.onSearch = onSearch
}
