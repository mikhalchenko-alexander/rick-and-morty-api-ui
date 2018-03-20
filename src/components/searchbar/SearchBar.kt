package components.searchbar

import components.button.buttonCustom
import components.searchbar.searchinput.searchInput
import react.*
import react.dom.div

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
            props.fields.map { (label, stateUpdater) ->
                searchInput(
                    label,
                    { setState { searchFilter = searchFilter.stateUpdater(it) } },
                    { props.onSearch(state.searchFilter) })
            }

            buttonCustom("Search!", setOf("SearchBar__Button"), { props.onSearch(state.searchFilter) })
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
