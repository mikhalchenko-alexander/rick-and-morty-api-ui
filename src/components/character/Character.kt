package components.character

import components.button.button
import components.itemcard.itemCard
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.img
import model.Character as CharacterModel

interface CharacterProps : RProps {
    var character: CharacterModel
    var showEpisodeList: ((CharacterModel) -> Unit)?
}

class Character : RComponent<CharacterProps, RState>() {

    override fun RBuilder.render() {
        with(props.character) {
            div(classes = "Character") {
                div(classes = "Character__Avatar") {
                    img {
                        attrs {
                            src = image
                        }
                    }
                }

                div {
                    itemCard(
                        setOf("Character__ItemCard"),
                        "Name:" to name,
                        "Status:" to status,
                        "Species:" to species,
                        "Gender:" to gender,
                        "Type:" to (type.takeIf { it.isNotEmpty() } ?: "N/A"),
                        "Origin:" to origin.name,
                        "Location:" to location.name
                    )

                    props.showEpisodeList?.let { showEpisodeList ->
                        button("Episodes", { showEpisodeList(props.character) })
                    }
                }
            }
        }
    }

}

fun RBuilder.character(character: CharacterModel, showEpisodeList: ((CharacterModel) -> Unit)?) = child(Character::class) {
    attrs.character = character
    attrs.showEpisodeList = showEpisodeList
}
