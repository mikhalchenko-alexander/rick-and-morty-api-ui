package components.location

import kotlinx.html.js.onClickFunction
import react.*
import model.Location as LocationModel
import react.dom.div
import react.dom.span

interface LocationProps : RProps {
    var location: LocationModel
    var showCharacterList: (List<String>) -> Unit
}

class Location : RComponent<LocationProps, RState>() {

    override fun RBuilder.render() {
        with(props.location) {
            div {
                row("Name:", name)
                row("Dimension:", dimension)
                row("Type:", type)
                div {
                    span {
                        +"Residents..."

                        attrs {
                            onClickFunction = { props.showCharacterList(props.location.residents.toList()) }
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.row(label: String, value: String) =
        div {
            span { +label }
            span { +value }
        }
}

fun RBuilder.location(location: LocationModel, showCharacterList: (List<String>) -> Unit) = child(Location::class) {
    attrs.location = location
    attrs.showCharacterList = showCharacterList
}
