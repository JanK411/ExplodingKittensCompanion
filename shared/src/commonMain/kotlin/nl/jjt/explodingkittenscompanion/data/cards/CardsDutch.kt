package nl.jjt.explodingkittenscompanion.data.cards

import nl.jjt.explodingkittenscompanion.data.Card
import nl.jjt.explodingkittenscompanion.data.CardText

/** Rulebook text transcribed from `docs/rules-source/rules_dutch.pdf`. */
val dutchCards: Map<Card, CardText> = mapOf(
    Card.EXPLODING_KITTEN to CardText(
        name = "Exploding Kitten",
        summary = "Je ligt uit het spel — tenzij je een Ontmantelkaart hebt.",
        details = listOf(
            "Laat de kaart meteen aan iedereen zien.",
            "Heb je een Ontmantelkaart? Speel die en je overleeft het.",
            "Geen Ontmantelkaart? Je bent dood. Leg al je kaarten af.",
        ),
        rulebook = "Laat deze kaart onmiddellijk zien. Als je geen Ontmantelkaart hebt, " +
            "dan ben je dood. Leg al je kaarten af, inclusief de Exploding Kitten.",
    ),
    Card.DEFUSE to CardText(
        name = "Ontmantel",
        summary = "Overleef een Exploding Kitten en stop hem terug waar jij wil.",
        details = listOf(
            "Alleen te spelen direct nadat je een Exploding Kitten trekt.",
            "Jij kiest in het geheim waar de Kitten terugkomt — bovenop, onderop, overal.",
            "Verander de volgorde van de andere kaarten niet en bekijk ze niet.",
            "Je beurt is voorbij; je trekt geen kaart meer.",
        ),
        rulebook = "Heb je een Exploding Kitten getrokken, speel dan direct deze kaart om " +
            "te overleven. Leg je Ontmantelkaart op de aflegstapel.\n\n" +
            "Pak dan de Exploding Kitten en steek deze op een positie naar keuze terug in " +
            "de stapel speelkaarten, zonder de volgorde te wijzigen of de kaarten te " +
            "bekijken.\n\n" +
            "Wil jij de volgende speler een loer draaien? Leg de Exploding Kitten dan " +
            "bovenop de stapel. Om dit ongezien te doen kun je de stapel onder tafel " +
            "houden zodat niemand kan zien waar de Exploding Kitten terecht komt. Je beurt " +
            "is voorbij na het plaatsen van de kaart.",
    ),
    Card.NOPE to CardText(
        name = "Nope",
        summary = "Maak de kaart die net gespeeld is ongedaan, alsof hij nooit gespeeld is.",
        details = listOf(
            "Altijd te spelen, ook als je niet aan de beurt bent.",
            "Werkt niet tegen een Exploding Kitten of een Ontmantelkaart.",
            "Een Nope op een Nope zet de kaart weer aan.",
        ),
        rulebook = "Stop een willekeurige actie behalve een Exploding Kitten of een " +
            "Ontmantelkaart. Elke kaart (of paar of speciale combinatie) die eerder " +
            "gespeeld is voorafgaand aan een Nope kaart, komt hiermee te vervallen.\n\n" +
            "Je mag een Nope kaart ook spelen op een andere Nope kaart om zijn effect " +
            "teniet te doen, enzovoort.\n\n" +
            "Je mag een Nope kaart altijd spelen voordat een actie begonnen is, zelfs als " +
            "dit niet in jouw beurt is. Alle kaarten die gestopt zijn door een Nope kaart, " +
            "komen te vervallen en blijven op de aflegstapel liggen.",
    ),
    Card.ATTACK to CardText(
        name = "Aanval",
        summary = "Beëindig je beurt zonder te trekken — de volgende speler doet 2 beurten.",
        details = listOf(
            "Je trekt zelf helemaal geen kaart.",
            "Het slachtoffer speelt een normale beurt en daarna meteen nog een.",
            "Speelt het slachtoffer ook een Aanval? Dan schuiven de beurten door.",
        ),
        rulebook = "Beëindig jouw beurt(en) zonder een kaart te trekken en dwing de " +
            "volgende speler om 2 beurten uit te voeren. Het slachtoffer speelt eerst een " +
            "normale beurt en speelt dan nogmaals. (Als het slachtoffer van deze " +
            "Aanvalkaart ook een Aanvalkaart speelt, dan gaan zijn beurten direct voorbij " +
            "en dient de volgende speler 2 beurten uit te voeren.)",
    ),
    Card.SKIP to CardText(
        name = "Sla Over",
        summary = "Beëindig je beurt meteen zonder een kaart te trekken.",
        details = listOf(
            "Veilige uitweg als je denkt dat de bovenste kaart een Exploding Kitten is.",
            "Tegen een Aanval eindigt hij maar 1 van de 2 beurten — je hebt er twee nodig.",
        ),
        rulebook = "Beëindig direct jouw beurt zonder een kaart te trekken. (Als je " +
            "bijvoorbeeld een Sla Over kaart speelt als reactie op een Aanvalkaart, dan is " +
            "slechts 1 van je 2 speelbeurten voorbij. Met het spelen van twee Sla Over " +
            "kaarten zou je beide beurten kunnen beëindigen.)",
    ),
    Card.FAVOR to CardText(
        name = "Kateautje",
        summary = "Kies een speler — die moet jou één kaart geven.",
        details = listOf(
            "Hij kiest zelf welke kaart je krijgt, niet jij.",
            "Je beurt gaat door; je moet aan het eind nog steeds trekken.",
        ),
        rulebook = "Kies een speler die jou 1 van zijn handkaarten dient te geven. De " +
            "gekozen speler mag zelf bepalen welke kaart hij jou geeft.",
    ),
    Card.SHUFFLE to CardText(
        name = "Schud",
        summary = "Schud de stapel speelkaarten.",
        details = listOf(
            "Handig als je weet dat er een Exploding Kitten bovenaan ligt.",
            "Je beurt gaat door; je moet aan het eind nog steeds trekken.",
        ),
        rulebook = "Schud de stapel speelkaarten grondig. (Erg handig als je weet dat er " +
            "een Exploding Kitten op komst is.)",
    ),
    Card.SEE_THE_FUTURE to CardText(
        name = "Voorspel de toekomst",
        summary = "Bekijk stiekem de bovenste 3 kaarten van de stapel.",
        details = listOf(
            "Leg ze terug in precies dezelfde volgorde.",
            "Laat ze aan niemand zien.",
            "Je beurt gaat door; je moet aan het eind nog steeds trekken.",
        ),
        rulebook = "Bekijk de bovenste 3 kaarten van de stapel en leg deze terug in " +
            "dezelfde volgorde. Laat deze kaarten niet zien aan de andere spelers.",
    ),
    Card.CAT_CARD to CardText(
        name = "Kattenkaarten",
        summary = "Alleen waardeloos. Twee dezelfde katten stelen een willekeurige kaart.",
        details = listOf(
            "Elke kaart met alleen een kat erop en geen instructies is een Kattenkaart.",
            "Twee dezelfde: steel een willekeurige kaart van een speler naar keuze.",
            "Drie dezelfde: noem de kaart die je wil — heeft hij die, dan moet hij hem geven.",
            "Vijf verschillende kaarten: pak een kaart naar keuze uit de aflegstapel.",
            "Combinaties werken met alle gelijke kaarten, niet alleen katten.",
        ),
        rulebook = "Kattenkaarten hebben geen waarde als ze alleen zijn, maar als je twee " +
            "dezelfde Kattenkaarten hebt, dan kun je deze als paar inzetten om een " +
            "willekeurige kaart van een andere speler te stelen.\n\n" +
            "TWEE DEZELFDE\nHet effect van een paar van 2 dezelfde Kattenkaarten (waarmee " +
            "je een willekeurige kaart van een andere speler kunt stelen) geldt niet " +
            "langer alleen voor Kattenkaarten, maar nu voor ALLE soorten kaarten met een " +
            "gelijke naam (een paar Schudkaarten, een paar Sla Over kaarten etc.).\n\n" +
            "DRIE DEZELFDE\nBij Drie Dezelfde heb je hetzelfde effect als bij Twee " +
            "Dezelfde, maar in dit geval mag jij zelf bepalen welke kaart je van een " +
            "andere speler wilt stelen. Als men deze kaart heeft, dan krijg jij hem, " +
            "anders krijg je niets.\n\n" +
            "5 VERSCHILLENDE KAARTEN\nAls je 5 verschillende kaarten speelt (5 " +
            "willekeurige kaarten met verschillende namen), dan mag je een willekeurige " +
            "kaart uit de aflegstapel pakken.\n\n" +
            "Als je combinaties speelt, dan kun je de instructies op deze kaarten negeren.",
    ),
)
