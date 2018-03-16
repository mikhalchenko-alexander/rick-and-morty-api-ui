package components.characterpage.charactersearch

import components.button.buttonCustom
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.input

interface CharacterSearchProps : RProps {
    var additionalClasses: Set<String>
}

class CharacterSearch : RComponent<CharacterSearchProps, RState>() {

    override fun RBuilder.render() {
        div(classes = "CharacterSearch") {
            attrs {
                classes += props.additionalClasses
            }

            placeholderInput("Name")
            placeholderInput("Status")
            placeholderInput("Species")
            placeholderInput("Gender")
            placeholderInput("Type")

            buttonCustom("Search!", setOf("CharacterSearch__Button"))
        }
    }

    private fun RBuilder.placeholderInput(placeholder: String) {
        input(classes = "CharacterSearch__SearchInput") {
            attrs {
                this@attrs.placeholder = placeholder
            }
        }
    }

}

fun RBuilder.characterSearch(additionalClasses: Set<String>) = child(CharacterSearch::class) {
    attrs.additionalClasses = additionalClasses
}
