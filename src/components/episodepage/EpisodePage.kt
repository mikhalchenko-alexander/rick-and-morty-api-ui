package components.episodepage

import api.getEpisodes
import components.episodelist.episodeList
import components.episodepage.episodesearch.EpisodeSearchFilter
import components.episodepage.episodesearch.episodeSearch
import components.itemcardpage.itemCardPage
import model.Episode
import model.Page
import react.*

interface EpisodePageProps : RProps {
    var showCharacterList: (List<String>) -> Unit
}

interface EpisodePageState : RState {
    var episodes: Page<Episode>?
}

class EpisodePage : RComponent<EpisodePageProps, EpisodePageState>() {

    override fun componentDidMount() {
        getEpisodes().then(::updateEpisodes)
    }

    override fun RBuilder.render() {
        episodeSearch(setOf("EpisodePage__EpisodeSearch"), ::onEpisodeSearch)

        state.episodes?.let { episodes ->
            itemCardPage(
                episodes,
                { pageUrl -> getEpisodes(url = pageUrl) },
                { episodeList(it, props.showCharacterList) },
                ::updateEpisodes
            )
        }
    }

    private fun onEpisodeSearch(episodeSearchFilter: EpisodeSearchFilter) {
        with(episodeSearchFilter) {
            getEpisodes(name, episode)
                .then(::updateEpisodes)
        }
    }

    private fun updateEpisodes(page: Page<Episode>) = setState {
        episodes = page
    }

}

fun RBuilder.episodePage(showCharacterList: (List<String>) -> Unit) = child(EpisodePage::class) {
    attrs.showCharacterList = showCharacterList
}
