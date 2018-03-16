package components.locationpage

import api.getLocations
import components.itemcardpage.itemCardPage
import components.locationlist.locationList
import react.*

interface LocationPageProps : RProps {
    var showCharacterList: (List<String>) -> Unit
}

class LocationPage(props: LocationPageProps) : RComponent<LocationPageProps, RState>(props) {

    override fun RBuilder.render() {
        itemCardPage({ getLocations() }, ::getLocations,
            { locations -> locationList(locations, props.showCharacterList) })
    }
}

fun RBuilder.locationPage(showCharacterList: (List<String>) -> Unit) = child(LocationPage::class) {
    attrs.showCharacterList = showCharacterList
}
