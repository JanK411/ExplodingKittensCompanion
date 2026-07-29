package nl.jjt.explodingkittenscompanion.ui

import explodingkittenscompanion.shared.generated.resources.Res
import explodingkittenscompanion.shared.generated.resources.ic_flag_de
import explodingkittenscompanion.shared.generated.resources.ic_flag_en
import explodingkittenscompanion.shared.generated.resources.ic_flag_nl
import explodingkittenscompanion.shared.generated.resources.ic_flag_pl
import nl.jjt.explodingkittenscompanion.data.Language
import org.jetbrains.compose.resources.DrawableResource

/**
 * The flag shown beside a [Language], mirroring [nl.jjt.explodingkittenscompanion.data.Card.style]:
 * it keeps `DrawableResource` out of the data layer, and the exhaustive `when` means the
 * compiler — not a blank row at runtime — tells you a new language still needs a flag.
 *
 * A flag is a country and not a language, which is a wart. It is worth it here because the
 * list is scanned by someone who cannot read any of the words on screen, and mitigated by
 * keeping [Language.nativeName] the actual label: the flag is decorative, so it carries no
 * `contentDescription` of its own.
 */
val Language.flag: DrawableResource
    get() = when (this) {
        Language.ENGLISH -> Res.drawable.ic_flag_en
        Language.DUTCH -> Res.drawable.ic_flag_nl
        Language.GERMAN -> Res.drawable.ic_flag_de
        Language.POLISH -> Res.drawable.ic_flag_pl
    }
