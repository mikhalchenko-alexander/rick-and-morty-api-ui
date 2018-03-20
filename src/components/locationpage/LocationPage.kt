package components.locationpage

import api.getLocations
import components.itemcardpage.itemCardPage
import components.locationlist.locationList
import components.locationpage.locationsearch.LocationSearchFilter
import components.locationpage.locationsearch.locationSearch
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
        locationSearch(setOf("LocationPage__LocationSearch"), ::onLocationSearch)

        state.locations?.let { locations ->
            itemCardPage(
                locations,
                { pageUrl -> getLocations(url = pageUrl) },
                { locationList(it, props.showCharacterList) },
                ::updateLocations)
        }
    }

    private fun onLocationSearch(locationSearchFilter: LocationSearchFilter) {
        with(locationSearchFilter) {
            getLocations(name, type, dimension)
                .then(::updateLocations)
        }
    }

    private fun updateLocations(page: Page<Location>) = setState {
        locations = page
    }

}

fun RBuilder.locationPage(showCharacterList: (List<String>) -> Unit) = child(LocationPage::class) {
    attrs.showCharacterList = showCharacterList
}
