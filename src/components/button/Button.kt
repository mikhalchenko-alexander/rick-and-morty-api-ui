package components.button

import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.div

interface ButtonProps : RProps {
    var label: String
    var onClickFunction: ((Event) -> Unit)?
    var additionalClasses: Set<String>
}

class Button: RComponent<ButtonProps, RState>() {
    override fun RBuilder.render() {
        div(classes = "Button") {
            attrs {
                classes += props.additionalClasses
                props.onClickFunction?.also { onClickFunction = it }
            }

            div(classes = "Button__Label") {
                +props.label
            }
        }
    }

}

fun RBuilder.buttonCustom(label: String, additionalClasses: Set<String> = emptySet(), onClickFunction: ((Event) -> Unit)? = {}) = child(Button::class) {
    attrs.label = label
    attrs.onClickFunction = onClickFunction
    attrs.additionalClasses = additionalClasses
}

fun RBuilder.button(label: String, onClickFunction: ((Event) -> Unit)? = undefined) = buttonCustom(label, emptySet(), onClickFunction)

fun RBuilder.buttonActive(label: String, additionalClasses: Set<String> = emptySet()) =
    buttonCustom(label, additionalClasses + "Button_active", undefined)

fun RBuilder.buttonDisabled(label: String, additionalClasses: Set<String> = emptySet()) =
    buttonCustom(label, additionalClasses + "Button_disabled", undefined)
