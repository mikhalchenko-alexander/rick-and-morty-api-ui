package components.searchbar

import components.button.buttonCustom
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onKeyPressFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div
import react.dom.input
import react.dom.label

interface SearchFilter

interface SearchBarProps<T : SearchFilter> : RProps {
    var initialFilter: T
    var fields: Map<String, T.(String) -> T>
    var onSearch: (T) -> Unit
}

interface SearchBarState<T : SearchFilter> : RState {
    var searchFilter: T
}

class SearchBar<T : SearchFilter>(props: SearchBarProps<T>) : RComponent<SearchBarProps<T>, SearchBarState<T>>(props) {

    override fun SearchBarState<T>.init(props: SearchBarProps<T>) {
        searchFilter = props.initialFilter
    }

    override fun RBuilder.render() {
        div(classes = "SearchBar") {
            props.fields.map { field ->
                searchInput(field.key, field.value)
            }

            buttonCustom("Search!", setOf("SearchBar__Button"), { props.onSearch(state.searchFilter) })
        }
    }

    private fun RBuilder.searchInput(label: String, stateUpdater: T.(String) -> T) {
        val id = label.toLowerCase().replace(" ", "-")
        label {
            attrs {
                set("htmlFor", id)
            }
            +"$label:"
        }
        input(classes = "SearchBar__SearchInput") {
            attrs {
                this@attrs.id = id
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    setState {
                        searchFilter = searchFilter.stateUpdater(target.value)
                    }
                }
                onKeyPressFunction = {
                    if (it.asDynamic().key == "Enter") {
                        props.onSearch(state.searchFilter)
                    }
                }
            }
        }
    }

}

fun <T : SearchFilter> RBuilder.searchBar(
    initialFilter: T, fields: Map<String, T.(String) -> T>, onSearch: (T) -> Unit) =
    child<SearchBarProps<T>, SearchBar<T>> {
        attrs.initialFilter = initialFilter
        attrs.fields = fields
        attrs.onSearch = onSearch
    }
