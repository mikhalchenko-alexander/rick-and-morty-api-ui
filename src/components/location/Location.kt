package components.location

import components.button.button
import components.itemcard.itemCard
import react.*
import model.Location as LocationModel
import react.dom.div

interface LocationProps : RProps {
    var location: LocationModel
    var showCharacterList: (List<String>) -> Unit
}

class Location : RComponent<LocationProps, RState>() {

    override fun RBuilder.render() {
        with(props.location) {
            div {
                itemCard(
                    emptySet(),
                    "Name:" to name,
                    "Dimension:" to dimension,
                    "Type:" to type
                )

                button("Residents...", { props.showCharacterList(props.location.residents.toList()) })
            }
        }
    }
}

fun RBuilder.location(location: LocationModel, showCharacterList: (List<String>) -> Unit) = child(Location::class) {
    attrs.location = location
    attrs.showCharacterList = showCharacterList
}
