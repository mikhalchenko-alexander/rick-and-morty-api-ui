package components.characterpage

import api.getCharacters
import components.abstractpage.abstractPage
import components.characterlist.characterList
import model.Character
import model.Page
import react.*

interface CharacterPageProps : RProps {
    var showEpisodeList: (List<String>) -> Unit
}

interface CharacterPageState : RState {
    var characters: Page<Character>?
}

class CharacterPage : RComponent<CharacterPageProps, CharacterPageState>() {

    override fun componentDidMount() {
        getCharacters().then { newCharacters ->
            setState {
                characters = newCharacters
            }
        }
    }

    override fun RBuilder.render() {
        abstractPage<Character> {
            attrs {
                getPage = ::getCharacters
                page = state.characters
                onPageLoad = { setState { characters = it } }
            }

            state.characters?.let { characters ->
                characterList(characters.results.toList(), props.showEpisodeList)
            }
        }
    }

}

fun RBuilder.characterPage(showEpisodeList: (List<String>) -> Unit) = child(CharacterPage::class) {
    attrs.showEpisodeList = showEpisodeList
}
