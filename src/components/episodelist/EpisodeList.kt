package components.episodelist

import components.episode.episode
import components.itemcardlist.itemCardList
import model.Episode
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

interface EpisodeListProps : RProps {
    var episodes: List<Episode>
    var showCharacterList: (List<String>) -> Unit
}

class EpisodeList : RComponent<EpisodeListProps, RState>() {

    override fun RBuilder.render() {
        itemCardList(props.episodes) { episode, additionalClasses ->
            episode(episode, additionalClasses, props.showCharacterList)
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
