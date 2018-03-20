package components.locationpage

import api.getLocations
import components.itemcardpage.itemCardPage
import components.locationlist.locationList
import model.Location
import model.Page
import react.*

interface LocationPageProps : RProps {
    var showCharacterList: (List<String>) -> Unit
}

interface LocationPageState : RState {
    var locations: Page<Location>?
}

class LocationPage(props: LocationPageProps) : RComponent<LocationPageProps, LocationPageState>(props) {

    override fun componentDidMount() {
        getLocations().then(::updateLocations)
    }

    override fun RBuilder.render() {
        state.locations?.let { locations ->
            itemCardPage(
                locations,
                ::getLocations,
                { locationList(it, props.showCharacterList) },
                ::updateLocations)
        }
    }

    private fun updateLocations(page: Page<Location>) = setState {
        locations = page
    }

}

fun RBuilder.locationPage(showCharacterList: (List<String>) -> Unit) = child(LocationPage::class) {
    attrs.showCharacterList = showCharacterList
}
