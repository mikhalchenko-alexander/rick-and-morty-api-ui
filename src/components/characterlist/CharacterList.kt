package components.characterlist

import api.getCharacters
import components.character.character
import model.Character
import react.*
import react.dom.div

interface CharacterListProps : RProps {
    var characterUrls: List<String>
    var showEpisodeList: (Character) -> Unit
}

interface CharacterListState : RState {
    var characters: List<Character>?
}

class CharacterList(props: CharacterListProps) : RComponent<CharacterListProps, CharacterListState>(props) {

    override fun componentDidMount() {
        getCharacters(props.characterUrls).then {
            setState {
                characters = it
            }
        }
    }

    override fun RBuilder.render() {
        div {
            state.characters?.map { character(it, props.showEpisodeList) }
        }
    }

}

fun RBuilder.characterList(characterUrls: List<String>, showEpisodeList: (Character) -> Unit) = child(CharacterList::class) {
    attrs.characterUrls = characterUrls
    attrs.showEpisodeList = showEpisodeList
}
