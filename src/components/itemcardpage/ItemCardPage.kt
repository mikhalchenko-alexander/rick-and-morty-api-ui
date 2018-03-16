package components.itemcardpage

import components.abstractpage.abstractPage
import model.Page
import react.*
import kotlin.js.Promise

interface ItemCardPageProps<T> : RProps {
    var getInitialItems: () -> Promise<Page<T>>
    var getItems: (String) -> Promise<Page<T>>
    var itemList: RBuilder.(List<T>) -> ReactElement
}

interface ItemCardPageState<T> : RState {
    var items: Page<T>?
}

class ItemCardPage<T> : RComponent<ItemCardPageProps<T>, ItemCardPageState<T>>() {

    override fun componentDidMount() {
        props.getInitialItems().then { newItems ->
            setState {
                items = newItems
            }
        }
    }

    override fun RBuilder.render() {
        abstractPage<T> {
            attrs {
                getPage = props.getItems
                page = state.items
                onPageLoad = { setState { items = it } }
            }
        }

        state.items?.results?.let { items ->
            props.itemList(this, items.toList())
        }
    }

}

fun <T> RBuilder.itemCardPage(getInitialItems: () -> Promise<Page<T>>,
                              getItems: (String) -> Promise<Page<T>>,
                              itemList: RBuilder.(List<T>) -> ReactElement) = child<ItemCardPageProps<T>, ItemCardPage<T>>() {
    attrs.getInitialItems = getInitialItems
    attrs.getItems = getItems
    attrs.itemList = itemList
}
