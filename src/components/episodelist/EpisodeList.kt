package components.episodelist

import components.episode.episode
import model.Episode
import react.*
import react.dom.div

interface EpisodeListProps : RProps {
    var episodes: List<Episode>
    var showCharacterList: (List<String>) -> Unit
}

class EpisodeList : RComponent<EpisodeListProps, RState>() {

    override fun RBuilder.render() {
        div(classes = "EpisodeList") {
            props.episodes.map { episode(it, setOf("EpisodeList__Episode"), props.showCharacterList) }
        }
    }

}

fun RBuilder.episodeList(
    episodes: List<Episode>,
    showCharacterList: (List<String>) -> Unit) =
    child(EpisodeList::class) {
        attrs.episodes = episodes
        attrs.showCharacterList = showCharacterList
    }
