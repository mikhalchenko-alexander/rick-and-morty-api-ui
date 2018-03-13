package components.navigation

import components.button.button
import react.*
import react.dom.div

interface NavigationProps : RProps {
    var showCharacterPage: () -> Unit
    var showEpisodePage: () -> Unit
    var showLocationPage: () -> Unit
    var activePage: ActivePage
}

enum class ActivePage {
    CHARACTERS,
    EPISODES,
    LOCATIONS,
    NONE
}

class Navigation : RComponent<NavigationProps, RState>() {

    override fun RBuilder.render() {
        div {
            button("Characters", props.activePage == ActivePage.CHARACTERS, props.showCharacterPage)
            button("Episodes", props.activePage == ActivePage.EPISODES, props.showEpisodePage)
            button("Locations", props.activePage == ActivePage.LOCATIONS, props.showLocationPage)
        }
    }

}

fun RBuilder.navigation(
    showCharacterPage: () -> Unit,
    showEpisodePage: () -> Unit,
    showLocationPage: () -> Unit,
    activePage: ActivePage
) = child(Navigation::class) {
    attrs.showCharacterPage = showCharacterPage
    attrs.showEpisodePage = showEpisodePage
    attrs.showLocationPage = showLocationPage
    attrs.activePage = activePage
}
