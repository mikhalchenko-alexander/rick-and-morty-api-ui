package components.itemcardlist

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

interface ItemCardListProps<T> : RProps {
    var items: List<T>
    var showItem: RBuilder.(T, Set<String>) -> Unit
}

class ItemCardList<T> : RComponent<ItemCardListProps<T>, RState>() {

    override fun RBuilder.render() {
        div(classes = "ItemCardList") {
            props.items.map { props.showItem(this, it, setOf("ItemCardList__ItemCard")) }
        }
    }

}

fun <T> RBuilder.itemCardList(items: List<T>, showItem: RBuilder.(T, Set<String>) -> Unit) =
    child<ItemCardListProps<T>, ItemCardList<T>> {
        attrs.items = items
        attrs.showItem = showItem
    }
