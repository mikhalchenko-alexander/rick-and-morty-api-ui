package components.locationpage

import api.getLocations
import components.abstractpage.abstractPage
import components.location.location
import model.Location
import model.Page
import react.*
import react.dom.div

interface LocationPageProps : RProps {
    var showCharacterList: (List<String>) -> Unit
}

interface LocationPageState : RState {
    var locations: Page<Location>?
}

class LocationPage(props: LocationPageProps) : RComponent<LocationPageProps, LocationPageState>(props) {

    override fun componentDidMount() {
        getLocations().then { newLocations ->
            setState {
                locations = newLocations
            }
        }
    }

    override fun RBuilder.render() {
        abstractPage<Location> {
            attrs {
                getPage = ::getLocations
                page = state.locations
                onPageLoad = { setState { locations = it } }
            }

            state.locations?.results?.toList()?.chunked(4) { locations ->
                div {
                    locations.map { location(it, props.showCharacterList) }
                }
            }
        }
    }

}

fun RBuilder.locationPage(showCharacterList: (List<String>) -> Unit) = child(LocationPage::class) {
    attrs.showCharacterList = showCharacterList
}
