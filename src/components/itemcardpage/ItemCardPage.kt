package components.itemcardpage

import components.abstractpage.abstractPage
import model.Page
import react.*
import kotlin.js.Promise

interface ItemCardPageProps<T> : RProps {
    var items: Page<T>
    var getItems: (String) -> Promise<Page<T>>
    var itemList: RBuilder.(List<T>) -> ReactElement
    var onPageLoad: (Page<T>) -> Unit
}

class ItemCardPage<T>(props: ItemCardPageProps<T>) : RComponent<ItemCardPageProps<T>, RState>(props) {

    override fun RBuilder.render() {
        abstractPage<T> {
            attrs {
                getPage = props.getItems
                page = props.items
                onPageLoad = props.onPageLoad
            }
        }

        props.itemList(this, props.items.results.toList())
    }

}

fun <T> RBuilder.itemCardPage(items: Page<T>,
                              getItems: (String) -> Promise<Page<T>>,
                              itemList: RBuilder.(List<T>) -> ReactElement,
                              onPageLoad: (Page<T>) -> Unit) = child<ItemCardPageProps<T>, ItemCardPage<T>> {
    attrs.items = items
    attrs.getItems = getItems
    attrs.itemList = itemList
    attrs.onPageLoad = onPageLoad
}
