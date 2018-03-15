package components.episodepage

import api.getEpisodes
import components.abstractpage.abstractPage
import components.episodelist.episodeList
import model.Episode
import model.Page
import react.*

interface EpisodePageProps : RProps {
    var showCharacterList: (List<String>) -> Unit
}

interface EpisodePageState : RState {
    var episodes: Page<Episode>?
}

class EpisodePage(props: EpisodePageProps) : RComponent<EpisodePageProps, EpisodePageState>(props) {

    override fun componentDidMount() {
        getEpisodes().then { newEpisodes ->
            setState {
                episodes = newEpisodes
            }
        }
    }

    override fun RBuilder.render() {
        abstractPage<Episode> {
            attrs {
                getPage = ::getEpisodes
                page = state.episodes
                onPageLoad = { setState { episodes = it } }
            }

            state.episodes?.results?.toList()?.let {
                episodeList(it, props.showCharacterList)
            }
        }
    }
}

fun RBuilder.episodePage(showCharacterList: (List<String>) -> Unit) = child(EpisodePage::class) {
    attrs.showCharacterList = showCharacterList
}
