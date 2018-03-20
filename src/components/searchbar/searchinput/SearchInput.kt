package components.searchbar.searchinput

import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onKeyPressFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.input
import react.dom.label

interface SearchInputProps : RProps {
    var label: String
    var onChangeF: (String) -> Unit
    var onSearchF: () -> Unit
}

class SearchInput : RComponent<SearchInputProps, RState>() {

    override fun RBuilder.render() {
        val id = props.label.toLowerCase().replace(" ", "-")
        label {
            attrs {
                set("htmlFor", id)
            }
            +"${props.label}:"
        }
        input(classes = "SearchBar__SearchInput") {
            attrs {
                this@attrs.id = id
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    props.onChangeF(target.value)
                }
                onKeyPressFunction = {
                    if (it.asDynamic().key == "Enter") {
                        props.onSearchF()
                    }
                }
            }
        }
    }

}

fun RBuilder.searchInput(label: String, onChangeF: (String) -> Unit, onSearchF: () -> Unit) = child(SearchInput::class) {
    attrs.label = label
    attrs.onChangeF = onChangeF
    attrs.onSearchF = onSearchF
}
