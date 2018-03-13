package components.character

import kotlinx.html.js.onClickFunction
import model.Character as CharacterModel
import react.*
import react.dom.div
import react.dom.img
import react.dom.span

interface CharacterProps : RProps {
    var character: CharacterModel
    var showEpisodeList: ((CharacterModel) -> Unit)?
}

class Character : RComponent<CharacterProps, RState>() {

    override fun RBuilder.render() {
        with(props.character) {
            div {
                div {
                    img {
                        attrs {
                            src = image
                        }
                    }
                }
                div {
                    row("Name:", name)
                    row("Status:", status)
                    row("Species:", species)
                    row("Gender:", gender)
                    row("Type:", if (type.isEmpty()) "N/A" else type)
                    row("Origin:", origin.name)
                    row("Location:", location.name)

                    props.showEpisodeList?.let { showEpisodeList ->
                        div {
                            span {
                                +"Episodes..."

                                attrs {
                                    onClickFunction = { showEpisodeList(props.character) }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.row(label: String, value: String) =
        div {
            span { +label }
            span { +value }
        }

}

fun RBuilder.character(character: CharacterModel, showEpisodeList: ((CharacterModel) -> Unit)?) = child(Character::class) {
    attrs.character = character
    attrs.showEpisodeList = showEpisodeList
}
