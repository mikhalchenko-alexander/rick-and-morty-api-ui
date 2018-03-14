package components.abstractpage

import components.button.button
import components.button.buttonDisabled
import model.Page
import react.*
import react.dom.div
import kotlin.js.Promise

interface PageProps<T> : RProps {
    var getPage: (String) -> Promise<Page<T>>
    var onPageLoad: (Page<T>) -> Unit
    var page: Page<T>?
}

class AbstractPage<T>(props: PageProps<T>) : RComponent<PageProps<T>, RState>(props) {

    private val abstractPageBtnClasses = setOf("AbstractPage__Button")

    override fun RBuilder.render() {
        div(classes = "AbstractPage App__AbstractPage") {
            listOf(
                "<" to props.page?.info?.previous,
                ">" to props.page?.info?.next
            ).map { (label, pageUrl) ->
                when {
                    pageUrl == null || pageUrl.isBlank() -> buttonDisabled(label, abstractPageBtnClasses)
                    else -> button(label, abstractPageBtnClasses, { props.getPage(pageUrl).then(props.onPageLoad) })
                }
            }
        }

        children()
    }

}

fun <T> RBuilder.abstractPage(handler: RHandler<PageProps<T>>) =
    child<PageProps<T>, AbstractPage<T>>(handler)
