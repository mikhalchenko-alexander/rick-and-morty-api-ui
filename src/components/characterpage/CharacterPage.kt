package components.characterpage

import api.getCharacters
import components.abstractpage.abstractPage
import components.characterlist.characterList
import components.characterpage.charactersearch.CharacterSearchFilter
import components.characterpage.charactersearch.characterSearch
import model.Character
import model.Page
import react.*
import react.dom.div

interface CharacterPageProps : RProps {
    var showEpisodeList: (List<String>) -> Unit
}

interface CharacterPageState : RState {
    var characters: Page<Character>?
}

class CharacterPage : RComponent<CharacterPageProps, CharacterPageState>() {

    override fun componentDidMount() {
        getCharacters().then(::updateCharacters)
    }

    override fun RBuilder.render() {
        div(classes = "CharacterPage") {
            characterSearch(setOf("CharacterPage__CharacterSearch"), ::onCharacterSearch)
            abstractPage<Character> {
                attrs {
                    getPage = { pageUrl -> getCharacters(url = pageUrl) }
                    page = state.characters
                    onPageLoad = { setState { characters = it } }
                }

                state.characters?.let { characters ->
                    characterList(characters.results.toList(), props.showEpisodeList)
                }
            }
        }
    }

    private fun onCharacterSearch(characterSearchFilter: CharacterSearchFilter) {
        with(characterSearchFilter) {
            getCharacters(name, status, species, gender, type)
                .then(::updateCharacters)
        }
    }

    private fun updateCharacters(page: Page<Character>) = setState {
        characters = page
    }

}

fun RBuilder.characterPage(showEpisodeList: (List<String>) -> Unit) = child(CharacterPage::class) {
    attrs.showEpisodeList = showEpisodeList
}
