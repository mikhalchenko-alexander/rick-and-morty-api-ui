package components.app

import components.characterlist.characterList
import components.characterpage.characterPage
import components.episodelist.episodeList
import components.episodepage.episodePage
import components.locationpage.locationPage
import components.navigation.ActivePage
import components.navigation.navigation
import model.Character
import react.*

sealed class Screen(val activePage: ActivePage)
object ScreenCharacterPage : Screen(ActivePage.CHARACTERS)
object ScreenEpisodePage : Screen(ActivePage.EPISODES)
object ScreenLocationPage : Screen(ActivePage.LOCATIONS)
class ScreenCharacterList(val characterList: List<String>) : Screen(ActivePage.NONE)
class ScreenEpisodeList(val character: Character) : Screen(ActivePage.NONE)

interface AppState : RState {
    var screen: Screen
}

class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        screen = ScreenCharacterPage
    }

    override fun RBuilder.render() {
        val currentScreen = state.screen
        navigation(
            showCharacterPage = { setScreen(ScreenCharacterPage) },
            showEpisodePage = { setScreen(ScreenEpisodePage) },
            showLocationPage = { setScreen(ScreenLocationPage) },
            activePage = currentScreen.activePage
        )

        when (currentScreen) {
            is ScreenCharacterList -> characterList(currentScreen.characterList, ::showEpisodeList)
            is ScreenCharacterPage -> characterPage(::showEpisodeList)
            is ScreenEpisodeList -> episodeList(currentScreen.character, ::showCharacterList)
            is ScreenEpisodePage -> episodePage(::showCharacterList)
            is ScreenLocationPage -> locationPage(::showCharacterList)
        }
    }

    private fun setScreen(scr: Screen) = setState { screen = scr }
    private fun showCharacterList(characterUrls: List<String>) = setScreen(ScreenCharacterList(characterUrls))
    private fun showEpisodeList(character: Character) = setScreen(ScreenEpisodeList(character))
}

fun RBuilder.app() = child(App::class) {}
