package components.app

import api.getCharacters
import api.getEpisodes
import components.characterlist.characterList
import components.characterpage.characterPage
import components.episodelist.episodeList
import components.episodepage.episodePage
import components.locationpage.locationPage
import components.navigation.ActivePage
import components.navigation.navigation
import model.Character
import model.Episode
import react.*

sealed class Screen(val activePage: ActivePage)
object ScreenCharacterPage : Screen(ActivePage.CHARACTERS)
object ScreenEpisodePage : Screen(ActivePage.EPISODES)
object ScreenLocationPage : Screen(ActivePage.LOCATIONS)
class ScreenCharacterList(val characterList: List<Character>) : Screen(ActivePage.NONE)
class ScreenEpisodeList(val episodes: List<Episode>) : Screen(ActivePage.NONE)

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
            is ScreenEpisodeList -> episodeList(currentScreen.episodes, ::showCharacterList)
            is ScreenEpisodePage -> episodePage(::showCharacterList)
            is ScreenLocationPage -> locationPage(::showCharacterList)
        }
    }

    private fun setScreen(scr: Screen) = setState { screen = scr }
    private fun showCharacterList(characterUrls: List<String>) {
        getCharacters(characterUrls).then {
            setScreen(ScreenCharacterList(it))
        }
    }
    private fun showEpisodeList(episodeUrls: List<String>) {
        getEpisodes(episodeUrls.toList()).then {
            setScreen(ScreenEpisodeList(it))
        }
    }
}

fun RBuilder.app() = child(App::class) {}
