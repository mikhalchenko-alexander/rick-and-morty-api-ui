package components.button

import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

interface ButtonProps : RProps {
    var label: String
    var isActive: Boolean
    var onClickF: () -> Unit
}

class Button: RComponent<ButtonProps, RState>() {
    override fun RBuilder.render() {
        div(classes = "Button") {
            +props.label

            attrs {
                if (props.isActive) {
                    classes += "Button_active"
                } else {
                    onClickFunction = { props.onClickF() }
                }
            }
        }
    }

}

fun RBuilder.button(label: String, isActive: Boolean, onClickF: () -> Unit) = child(Button::class) {
    attrs.label = label
    attrs.isActive = isActive
    attrs.onClickF = onClickF
}
