package components.character

import components.button.button
import components.itemcard.itemCard
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.img
import model.Character as CharacterModel

interface CharacterProps : RProps {
    var character: CharacterModel
    var showEpisodeList: ((List<String>) -> Unit)?
    var additionalClasses: Set<String>
}

class Character : RComponent<CharacterProps, RState>() {

    override fun RBuilder.render() {
        with(props.character) {
            div(classes = "Character") {
                attrs {
                    classes += props.additionalClasses
                }

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
                        button("Episodes", { showEpisodeList(props.character.episode.toList()) })
                    }
                }
            }
        }
    }

}

fun RBuilder.character(character: CharacterModel, showEpisodeList: ((List<String>) -> Unit)?, additionalClasses: Set<String> = emptySet()) =
    child(Character::class) {
        attrs.character = character
        attrs.showEpisodeList = showEpisodeList
        attrs.additionalClasses = additionalClasses
    }
