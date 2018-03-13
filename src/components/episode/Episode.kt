package components.episode

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.div
import react.dom.span
import model.Episode as EpisodeModel

interface EpisodeProps : RProps {
    var episode: EpisodeModel
    var showCharacterList: (List<String>) -> Unit
}

class Episode : RComponent<EpisodeProps, RState>() {

    override fun RBuilder.render() {
        with(props.episode) {
            div {
                row("Name:", name)
                row("Air date:", airDate)
                row("Episode:", episode)
                div {
                    span {
                        +"Characters..."

                        attrs {
                            onClickFunction = { props.showCharacterList(props.episode.characters.toList()) }
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

fun RBuilder.episode(episode: EpisodeModel, showCharacterList: (List<String>) -> Unit) = child(Episode::class) {
    attrs.episode = episode
    attrs.showCharacterList = showCharacterList
}
