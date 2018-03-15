package components.characterlist

import components.character.character
import model.Character
import react.*
import react.dom.div

interface CharacterListProps : RProps {
    var characters: List<Character>
    var showEpisodeList: (List<String>) -> Unit
}

class CharacterList(props: CharacterListProps) : RComponent<CharacterListProps, RState>(props) {

    override fun RBuilder.render() {
        div(classes = "CharacterList") {
            props.characters.map { character(it, props.showEpisodeList, setOf("CharacterList__Character")) }
        }
    }

}

fun RBuilder.characterList(characters: List<Character>, showEpisodeList: (List<String>) -> Unit) = child(CharacterList::class) {
    attrs.characters = characters
    attrs.showEpisodeList = showEpisodeList
}
