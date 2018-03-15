package components.location

import components.button.button
import components.itemcard.itemCard
import kotlinx.html.classes
import react.*
import model.Location as LocationModel
import react.dom.div

interface LocationProps : RProps {
    var location: LocationModel
    var showCharacterList: (List<String>) -> Unit
    var additionalClasses: Set<String>
}

class Location : RComponent<LocationProps, RState>() {

    override fun RBuilder.render() {
        with(props.location) {
            div(classes = "Location") {
                attrs {
                    classes += props.additionalClasses
                }

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

fun RBuilder.location(location: LocationModel, additionalClasses: Set<String>, showCharacterList: (List<String>) -> Unit) = child(Location::class) {
    attrs.location = location
    attrs.showCharacterList = showCharacterList
    attrs.additionalClasses = additionalClasses
}
