package components.navigation

import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
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
            btn("Characters", ActivePage.CHARACTERS, props.showCharacterPage)
            btn("Episodes", ActivePage.EPISODES, props.showEpisodePage)
            btn("Locations", ActivePage.LOCATIONS, props.showLocationPage)
        }
    }

    private fun RBuilder.btn(label: String, activePage: ActivePage, onClickF: () -> Unit) {
        div {
            +label

            attrs {
                if (props.activePage == activePage) {
                    classes += "???"
                } else {
                    onClickFunction = { onClickF() }
                }
            }
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
