package components.episode

import components.button.button
import components.itemcard.itemCard
import react.*
import react.dom.div
import model.Episode as EpisodeModel

interface EpisodeProps : RProps {
    var episode: EpisodeModel
    var showCharacterList: (List<String>) -> Unit
}

class Episode : RComponent<EpisodeProps, RState>() {

    override fun RBuilder.render() {
        with(props.episode) {
            div {
                itemCard(
                    setOf("Character__ItemCard"),
                    "Name:" to name,
                    "Air date:" to airDate,
                    "Episode:" to episode
                )

                button("Characters...", { props.showCharacterList(props.episode.characters.toList()) })
            }
        }
    }

}

fun RBuilder.episode(episode: EpisodeModel, showCharacterList: (List<String>) -> Unit) = child(Episode::class) {
    attrs.episode = episode
    attrs.showCharacterList = showCharacterList
}
