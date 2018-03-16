package components.locationlist

import components.location.location
import model.Location
import react.*
import react.dom.div

interface LocationListProps : RProps {
    var locations: List<Location>
    var showCharacterList: (List<String>) -> Unit
}

class LocationList : RComponent<LocationListProps, RState>() {

    override fun RBuilder.render() {
        div(classes = "LocationList") {
            props.locations.map { location(it, setOf("LocationList__Location"), props.showCharacterList) }
        }
    }

}

fun RBuilder.locationList(
    locations: List<Location>,
    showCharacterList: (List<String>) -> Unit) =
    child(LocationList::class) {
        attrs.locations = locations
        attrs.showCharacterList = showCharacterList
    }
