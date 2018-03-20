package components.itemcard

import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

interface ItemCardProps : RProps {
    var rows: Map<String, String>
    var additionalClasses: Set<String>
}

class ItemCard : RComponent<ItemCardProps, RState>() {

    override fun RBuilder.render() {
        div(classes = "ItemCard") {
            attrs {
                classes += props.additionalClasses
            }

            props.rows.map { (key, value) ->
                div(classes = "ItemCard__ItemCardRow") {
                    div(classes = "ItemCard__ItemCardRowKey") { +key }
                    div(classes = "ItemCard__ItemCardRowValue") { +value }
                }
            }
        }
    }

}

fun RBuilder.itemCard(additionalClasses: Set<String>, vararg rows: Pair<String, String>) = child(ItemCard::class) {
    attrs.rows = rows.toMap()
    attrs.additionalClasses = additionalClasses
}
