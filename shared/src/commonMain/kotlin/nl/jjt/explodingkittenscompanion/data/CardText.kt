package nl.jjt.explodingkittenscompanion.data

/**
 * Everything the app knows about one [Card] in one [Language].
 *
 * Three levels of explanation, so the common case stays fast without dead-ending the
 * uncommon one:
 *
 * - [name] is the text printed on the physical card. In the game's language it is what the
 *   player matches against; in their own language it is what the card is called.
 * - [summary] answers "what do I do" in one line.
 * - [details] cover the edge cases people actually argue about.
 * - [rulebook] is the verbatim paragraph from the official rules, shown behind a "read more"
 *   toggle for when the short version still is not enough.
 */
data class CardText(
    val name: String,
    val summary: String,
    val details: List<String>,
    val rulebook: String,
)
