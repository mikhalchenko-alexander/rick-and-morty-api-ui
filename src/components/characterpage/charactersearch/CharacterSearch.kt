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

            placeholderInput("name", "Name", { this.copy(name = it) })
            placeholderInput("status", "Status", { this.copy(status = it) })
            placeholderInput("species", "Species", { this.copy(species = it) })
            placeholderInput("gender", "Gender", { this.copy(gender = it) })
            placeholderInput("type", "Type", { this.copy(type = it) })

            buttonCustom("Search!", setOf("CharacterSearch__Button"), { props.onSearch(state.characterSearchFilter) })
        }
    }

    private fun RBuilder.placeholderInput(id: String, label: String, stateUpdater: CharacterSearchFilter.(String) -> CharacterSearchFilter) {
        label {
            attrs {
                set("htmlFor", label.toLowerCase())
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
