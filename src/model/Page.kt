package model

interface Page<T> {
    val info: Info
    val results: Array<T>
}

interface Info {
    val count: Int
    val pages: Int
    val next: String
    @JsName("prev")
    val previous: String
}
