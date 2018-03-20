package components.locationpage.locationsearch

import components.searchbar.SearchFilter
import components.searchbar.searchBar
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

interface LocationSearchProps : RProps {
    var additionalClasses: Set<String>
    var onSearch: (LocationSearchFilter) -> Unit
}

data class LocationSearchFilter(
    val name: String = "",
    val type: String = "",
    val dimension: String = ""
) : SearchFilter

class LocationSearch : RComponent<LocationSearchProps, RState>() {

    override fun RBuilder.render() {
        div(classes = "LocationPage__LocationSearch") {
            attrs {
                classes += props.additionalClasses
            }

            searchBar(
                LocationSearchFilter(),
                mapOf(
                    "Name" to { value -> this.copy(name = value) },
                    "Type" to { value -> this.copy(type = value) },
                    "Dimension" to { value -> this.copy(dimension = value) }
                )) { props.onSearch(it) }
        }
    }

}

fun RBuilder.locationSearch(additionalClasses: Set<String>, onSearch: (LocationSearchFilter) -> Unit) = child(LocationSearch::class) {
    attrs.additionalClasses = additionalClasses
    attrs.onSearch = onSearch
}
