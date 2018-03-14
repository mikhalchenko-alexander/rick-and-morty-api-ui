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
    var onClickF: () -> Unit // TODO: Make it (Event) -> Unit
    var additionalClasses: Set<String>
}

class Button: RComponent<ButtonProps, RState>() {
    override fun RBuilder.render() {
        div(classes = "Button") {
            attrs {
                classes += props.additionalClasses
                if (props.isActive) { classes += "Button_active" }
                else { onClickFunction = { props.onClickF() } }
            }

            div(classes = "Button__Label") {
                +props.label
            }
        }
    }

}

fun RBuilder.button(label: String, isActive: Boolean, additionalClasses: Set<String>, onClickF: () -> Unit) = child(Button::class) {
    attrs.label = label
    attrs.isActive = isActive
    attrs.onClickF = onClickF
    attrs.additionalClasses = additionalClasses
}

fun RBuilder.button(label: String, isActive: Boolean, additionalClasses: String, onClickF: () -> Unit) =
    button(label, isActive, additionalClasses.split("\\s+").toSet(), onClickF)
