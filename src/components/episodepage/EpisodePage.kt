package components.episodepage

import api.getEpisodes
import components.episodelist.episodeList
import components.itemcardpage.itemCardPage
import react.*

interface EpisodePageProps : RProps {
    var showCharacterList: (List<String>) -> Unit
}

class EpisodePage(props: EpisodePageProps) : RComponent<EpisodePageProps, RState>(props) {

    override fun RBuilder.render() {
        itemCardPage({ getEpisodes() }, ::getEpisodes,
            { episodes -> episodeList(episodes, props.showCharacterList) })
    }
}

fun RBuilder.episodePage(showCharacterList: (List<String>) -> Unit) = child(EpisodePage::class) {
    attrs.showCharacterList = showCharacterList
}
