package components.episodelist

import api.getEpisodes
import components.character.character
import components.episode.episode
import model.Character
import model.Episode
import react.*
import react.dom.div

interface EpisodeListProps : RProps {
    var character: Character
    var showEpisodeList: (Character) -> Unit
    var showCharacterList: (List<String>) -> Unit
}

interface EpisodeListState : RState {
    var episodes: List<Episode>?
}

class EpisodeList : RComponent<EpisodeListProps, EpisodeListState>() {

    override fun componentDidMount() {
        getEpisodes(props.character.episode.toList()).then {
            setState {
                episodes = it
            }
        }
    }

    override fun RBuilder.render() {
        character(props.character, props.showEpisodeList)
        state.episodes?.chunked(4) { episodes ->
            div {
                episodes.map { episode(it, props.showCharacterList) }
            }
        }
    }

}

fun RBuilder.episodeList(
    character: Character,
    showCharacterList: (List<String>) -> Unit) =
    child(EpisodeList::class) {
        attrs.character = character
        attrs.showCharacterList = showCharacterList
    }
