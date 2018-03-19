package components.characterpage.charactersearch

import components.button.buttonCustom
import kotlinx.html.classes
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div
import react.dom.input
import react.dom.label

interface CharacterSearchProps : RProps {
    var additionalClasses: Set<String>
}

interface CharacterSearchState : RState {
    var name: String
    var status: String
    var species: String
    var gender: String
    var type: String
}

class CharacterSearch : RComponent<CharacterSearchProps, CharacterSearchState>() {

    override fun RBuilder.render() {
        div(classes = "CharacterSearch") {
            attrs {
                classes += props.additionalClasses
            }

            placeholderInput("name", "Name", { name = it })
            placeholderInput("status", "Status", { status = it })
            placeholderInput("species", "Species", { species = it })
            placeholderInput("gender", "Gender", { gender = it })
            placeholderInput("type", "Type", { type = it })

            buttonCustom("Search!", setOf("CharacterSearch__Button"))
        }
    }

    private fun RBuilder.placeholderInput(id: String, label: String, stateUpdater: CharacterSearchState.(String) -> Unit) {
        label {
            attrs {
                htmlFor = label.toLowerCase()
            }
            +"$label:"
        }
        input(classes = "CharacterSearch__SearchInput") {
            attrs {
                this@attrs.id = id
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    setState {
                        stateUpdater(target.value)
                    }
                }
            }
        }
    }

}

fun RBuilder.characterSearch(additionalClasses: Set<String>) = child(CharacterSearch::class) {
    attrs.additionalClasses = additionalClasses
}
