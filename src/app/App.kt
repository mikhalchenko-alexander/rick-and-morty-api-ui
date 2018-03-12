package app

import react.*

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
    }
}

fun RBuilder.app() = child(App::class) {}
