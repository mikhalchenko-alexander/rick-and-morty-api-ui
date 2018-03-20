package components.episode

import components.button.button
import components.itemcard.itemCard
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import model.Episode as EpisodeModel

interface EpisodeProps : RProps {
    var episode: EpisodeModel
    var showCharacterList: (List<String>) -> Unit
    var additionalClasses: Set<String>
}

class Episode : RComponent<EpisodeProps, RState>() {

    override fun RBuilder.render() {
        with(props.episode) {
            div(classes = "Episode") {
                attrs {
                    classes += props.additionalClasses
                }
                itemCard(
                    setOf("Episode__ItemCard"),
                    "Name:" to name,
                    "Air date:" to airDate,
                    "Episode:" to episode
                )

                button("Characters...", { props.showCharacterList(props.episode.characters.toList()) })
            }
        }
    }

}

fun RBuilder.episode(episode: EpisodeModel, additionalClasses: Set<String>, showCharacterList: (List<String>) -> Unit) = child(Episode::class) {
    attrs.episode = episode
    attrs.showCharacterList = showCharacterList
    attrs.additionalClasses = additionalClasses
}
