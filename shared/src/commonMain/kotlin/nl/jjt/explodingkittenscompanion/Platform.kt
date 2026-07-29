package nl.jjt.explodingkittenscompanion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform