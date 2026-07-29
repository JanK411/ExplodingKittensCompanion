package nl.jjt.explodingkittenscompanion.data.cards

import nl.jjt.explodingkittenscompanion.data.Card
import nl.jjt.explodingkittenscompanion.data.CardText

/** Rulebook text transcribed from `docs/rules-source/rules_german.pdf`. */
val germanCards: Map<Card, CardText> = mapOf(
    Card.EXPLODING_KITTEN to CardText(
        name = "Exploding Kitten",
        summary = "Du bist raus — außer du hast eine Entschärfung.",
        details = listOf(
            "Zeige die Karte sofort allen.",
            "Du hast eine Entschärfung? Spiel sie aus und du überlebst.",
            "Keine Entschärfung? Du bist tot. Lege deine ganze Hand ab.",
        ),
        rulebook = "Diese Karte musst du sofort offen zeigen. Solltest du keine " +
            "„Entschärfung\" mehr besitzen, war's das. Alle deine restlichen Karten und " +
            "das Exploding Kitten wandern auf den Ablagestapel.",
    ),
    Card.DEFUSE to CardText(
        name = "Entschärfung",
        summary = "Überlebe ein Exploding Kitten und verstecke es wieder im Stapel.",
        details = listOf(
            "Nur direkt nach dem Ziehen eines Exploding Kittens spielbar.",
            "Du bestimmst heimlich, wo das Kitten hinkommt — ganz oben, unten, überall.",
            "Die anderen Karten dabei nicht ansehen oder umsortieren.",
            "Dein Zug ist beendet; du ziehst keine Karte mehr.",
        ),
        rulebook = "Wenn du ein Exploding Kitten ziehst, kannst du eine „Entschärfung\" " +
            "ausspielen, statt zu sterben. Spiele sie einfach aus und lege sie auf den " +
            "Ablagestapel.\n\n" +
            "Lege danach das Exploding Kitten zurück in den Spielstapel, und zwar geheim " +
            "an eine Stelle deiner Wahl, ohne die anderen Karten anzusehen oder " +
            "umzusortieren.\n\n" +
            "Du willst dem nächsten Spieler eins auswischen? Lege das Exploding Kitten " +
            "ganz oben auf den Spielstapel. Mach das z. B. unter dem Tisch, damit niemand " +
            "sieht, an welche Stelle du das Kitten zurücklegst. Dann ist dein Spielzug " +
            "beendet.",
    ),
    Card.NOPE to CardText(
        name = "Nö!",
        summary = "Setzt die eben gespielte Karte außer Kraft, als wäre sie nie gespielt worden.",
        details = listOf(
            "Jederzeit spielbar, auch wenn du nicht an der Reihe bist.",
            "Wirkt nicht gegen Exploding Kitten oder Entschärfung.",
            "Ein NÖ! auf ein NÖ! macht daraus ein DOCH! — die Karte wirkt wieder.",
        ),
        rulebook = "Mit NÖ! setzt du eine andere Karte und deren Aktion außer Kraft, " +
            "ausgenommen Exploding Kittens und Entschärfung. Es ist so, als würde sich die " +
            "Karte, das Pärchen oder die Kombination durch ein NÖ! in Luft auflösen.\n\n" +
            "Du kannst ein NÖ! auf ein anderes NÖ! legen, um es aufzuheben und daraus ein " +
            "DOCH! zu machen.\n\n" +
            "Du kannst ein NÖ! auch spielen, wenn du nicht an der Reihe bist. Alle Karten, " +
            "die ge-NÖ!-t wurden, sind raus und bleiben auf dem Ablagestapel.",
    ),
    Card.ATTACK to CardText(
        name = "Angriff",
        summary = "Beende deinen Zug ohne zu ziehen — der nächste Spieler macht 2 Züge.",
        details = listOf(
            "Du ziehst selbst überhaupt keine Karte.",
            "Das Opfer macht einen normalen Zug und direkt danach noch einen.",
            "Spielt das Opfer selbst Angriff, wandern die Züge weiter zum nächsten Spieler.",
        ),
        rulebook = "Du beendest deinen eigenen Zug, ohne eine Karte zu ziehen, und zwingst " +
            "den nächsten Spieler, zwei Spielzüge direkt nacheinander auszuführen. Dein " +
            "Opfer macht seinen ersten Zug und direkt danach noch einen. (Spielt dein " +
            "Opfer dabei selbst eine Karte „Angriff\" aus, ist er nicht mehr an der Reihe " +
            "und der nächste Spieler muss zwei Spielzüge ausführen.)",
    ),
    Card.SKIP to CardText(
        name = "Hops!",
        summary = "Beende deinen Zug sofort, ohne eine Karte zu ziehen.",
        details = listOf(
            "Sicherer Ausweg, wenn du ein Exploding Kitten oben vermutest.",
            "Gegen einen Angriff beendet sie nur 1 der 2 Züge — du brauchst zwei davon.",
        ),
        rulebook = "Beende sofort deinen Zug, ohne eine Karte zu ziehen. (Falls du " +
            "„Hops!\" ausspielst, um einen Angriff abzuwehren, überspringst du nur einen " +
            "der zwei Züge. Du müsstest schon zweimal „Hops!\" ausspielen, um beide Züge " +
            "zu beenden.)",
    ),
    Card.FAVOR to CardText(
        name = "Wunsch",
        summary = "Wähle einen Mitspieler — er muss dir eine Karte geben.",
        details = listOf(
            "Er entscheidet, welche Karte du bekommst, nicht du.",
            "Dein Zug läuft weiter; am Ende musst du trotzdem ziehen.",
        ),
        rulebook = "Zwinge einen Mitspieler deiner Wahl, dir eine Karte zu geben. Dieser " +
            "Spieler entscheidet, welche Karte du bekommst.",
    ),
    Card.SHUFFLE to CardText(
        name = "Mischen",
        summary = "Mische den Spielstapel neu.",
        details = listOf(
            "Nützlich, wenn du weißt, dass oben ein Exploding Kitten liegt.",
            "Dein Zug läuft weiter; am Ende musst du trotzdem ziehen.",
        ),
        rulebook = "Misch den Spielstapel sorgfältig neu. (Diese Karte ist besonders " +
            "nützlich, wenn du weißt, dass ein Exploding Kitten oben auf dem Stapel liegt.)",
    ),
    Card.SEE_THE_FUTURE to CardText(
        name = "Blick in die Zukunft",
        summary = "Sieh dir heimlich die obersten 3 Karten des Stapels an.",
        details = listOf(
            "Lege sie in genau derselben Reihenfolge zurück.",
            "Zeige sie niemandem.",
            "Dein Zug läuft weiter; am Ende musst du trotzdem ziehen.",
        ),
        rulebook = "Schau dir die obersten drei Karten des Spielstapels an und lege sie " +
            "zurück, ohne deren Reihenfolge zu verändern. Zeige diese Karten bloß nicht " +
            "deinen Mitspielern.",
    ),
    Card.CAT_CARD to CardText(
        name = "Katzen-Karten",
        summary = "Einzeln machtlos. Zwei gleiche Katzen stehlen eine zufällige Karte.",
        details = listOf(
            "Jede Karte mit nur einem Katzenbild und ohne Anweisung ist eine Katzen-Karte.",
            "Pärchen: stiehl eine zufällige Karte von einem Mitspieler deiner Wahl.",
            "Drilling: nenne die gewünschte Karte — hat er sie, muss er sie herausgeben.",
            "Fünf verschiedene Karten: nimm dir eine beliebige Karte aus dem Ablagestapel.",
            "Kombinationen gehen mit allen gleichen Karten, nicht nur mit Katzen.",
        ),
        rulebook = "Einzeln sind diese Karten machtlos, doch wenn du 2 gleiche " +
            "Katzen-Karten hast, kannst du sie als Pärchen spielen, um eine zufällige " +
            "Karte von einem Mitspieler zu stehlen. Oder du nutzt sie für eine andere " +
            "Kombination.\n\n" +
            "PÄRCHEN\nJetzt können ALLE gleichen Karten als Pärchen gespielt werden, um " +
            "einem Mitspieler eine zufällige Karte zu stehlen. Die Regel gilt also nicht " +
            "mehr nur für Katzen-Karten, sondern für alle Karten mit dem gleichen Titel " +
            "(ein Pärchen Wunsch-Karten, ein Pärchen Hops!-Karten ...).\n\n" +
            "DRILLING\nWie ein Pärchen, außer dass du dir eine Karte von dem Mitspieler " +
            "wünschen darfst. Besitzt er solch eine Karte, muss er sie dir geben. Hat er " +
            "keine solche Karte, hast du Pech gehabt.\n\n" +
            "FÜNFLING\nWenn du 5 verschiedene Karten (jede mit einem anderen Titel) " +
            "spielst, darfst du dir eine beliebige Karte aus dem Ablagestapel nehmen.\n\n" +
            "Wenn du eine Kombination spielst, gelten die Anweisungen auf den Karten nicht.",
    ),
)
