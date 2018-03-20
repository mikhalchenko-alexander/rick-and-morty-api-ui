package components.episodepage.episodesearch

import components.searchbar.SearchFilter
import components.searchbar.searchBar
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

interface EpisodeSearchProps : RProps {
    var additionalClasses: Set<String>
    var onSearch: (EpisodeSearchFilter) -> Unit
}

data class EpisodeSearchFilter(
    val name: String = "",
    val episode: String = ""
) : SearchFilter

class EpisodeSearch : RComponent<EpisodeSearchProps, RState>() {

    override fun RBuilder.render() {
        div(classes = "EpisodePage__EpisodeSearch") {
            attrs {
                classes += props.additionalClasses
            }

            searchBar(
                EpisodeSearchFilter(),
                mapOf(
                    "Name" to { value -> this.copy(name = value) },
                    "Episode" to { value -> this.copy(episode = value) }
                )) { props.onSearch(it) }
        }
    }

}

fun RBuilder.episodeSearch(additionalClasses: Set<String>, onSearch: (EpisodeSearchFilter) -> Unit) = child(EpisodeSearch::class) {
    attrs.additionalClasses = additionalClasses
    attrs.onSearch = onSearch
}
