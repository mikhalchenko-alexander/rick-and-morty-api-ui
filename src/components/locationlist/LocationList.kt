package components.locationlist

import components.itemcardlist.itemCardList
import components.location.location
import model.Location
import react.*

interface LocationListProps : RProps {
    var locations: List<Location>
    var showCharacterList: (List<String>) -> Unit
}

class LocationList : RComponent<LocationListProps, RState>() {

    override fun RBuilder.render() {
        itemCardList(props.locations) { location, additionalClasses ->
            location(location, additionalClasses, props.showCharacterList)
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
