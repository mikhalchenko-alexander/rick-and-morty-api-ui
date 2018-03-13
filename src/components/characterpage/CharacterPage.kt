package components.characterpage

import api.getCharacters
import components.abstractpage.abstractPage
import components.character.character
import model.Character
import model.Page
import react.*
import react.dom.div

interface CharacterPageProps : RProps {
    var showEpisodeList: (Character) -> Unit
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
                div {
                    characters.results.map { character(it, props.showEpisodeList) }
                }
            }
        }
    }

}

fun RBuilder.characterPage(showEpisodeList: (Character) -> Unit) = child(CharacterPage::class) {
    attrs.showEpisodeList = showEpisodeList
}
