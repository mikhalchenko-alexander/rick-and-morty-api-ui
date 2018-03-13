package components.abstractpage

import kotlinx.html.js.onClickFunction
import model.Page
import react.*
import react.dom.div
import react.dom.span
import kotlin.js.Promise

interface PageProps<T> : RProps {
    var getPage: (String) -> Promise<Page<T>>
    var onPageLoad: (Page<T>) -> Unit
    var page: Page<T>?
}

class AbstractPage<T>(props: PageProps<T>) : RComponent<PageProps<T>, RState>(props) {

    override fun RBuilder.render() {
        div {
            pagingButton("<", props.page?.info?.previous)
            pagingButton(">", props.page?.info?.next)
        }

        children()
    }

    private fun RBuilder.pagingButton(label: String, pageUrl: String?) {
        pageUrl?.takeIf { it.isNotEmpty() }?.also {
            span {
                +label
                attrs {
                    onClickFunction = {
                        props.getPage(pageUrl).then(props.onPageLoad)
                    }
                }
            }
        } ?: span {
            +label
        }
    }

}

fun <T> RBuilder.abstractPage(handler: RHandler<PageProps<T>>) =
    child<PageProps<T>, AbstractPage<T>>(handler)
