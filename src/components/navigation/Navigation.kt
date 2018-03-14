package components.navigation

import components.button.buttonActive
import components.button.buttonCustom
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
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

    private val navBtnClasses = setOf("Navigation__Button")

    override fun RBuilder.render() {
        div(classes = "Navigation App__Navigation") {
            listOf(
                Triple("Characters", ActivePage.CHARACTERS, props.showCharacterPage),
                Triple("Episodes", ActivePage.EPISODES, props.showEpisodePage),
                Triple("Locations", ActivePage.LOCATIONS, props.showLocationPage)
            ).map { (label, active, onClickFunction) ->
                when (active) {
                    props.activePage -> buttonActive(label, navBtnClasses)
                    else -> buttonCustom(label, navBtnClasses, { onClickFunction() })
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
