package nl.jjt.explodingkittenscompanion.data.cards

import nl.jjt.explodingkittenscompanion.data.Card
import nl.jjt.explodingkittenscompanion.data.CardText

/** Rulebook text transcribed from `docs/rules-source/rules_polish.pdf` (2023 printing). */
val polishCards: Map<Card, CardText> = mapOf(
    Card.EXPLODING_KITTEN to CardText(
        name = "Eksplodujący Kotek",
        summary = "Odpadasz z gry — chyba że masz kartę Rozbrój.",
        details = listOf(
            "Natychmiast pokaż tę kartę wszystkim.",
            "Masz Rozbrój? Zagraj go i przeżyjesz.",
            "Nie masz? Umierasz. Odrzuć całą swoją rękę.",
        ),
        rulebook = "Musisz natychmiast odkryć tę kartę. O ile nie masz karty rozbrojenia " +
            "– umierasz. Odrzuć wszystkie swoje karty, włączając w to eksplodującego kotka.",
    ),
    Card.DEFUSE to CardText(
        name = "Rozbrój",
        summary = "Przeżyj eksplodującego kotka i schowaj go z powrotem, gdzie chcesz.",
        details = listOf(
            "Możesz go zagrać tylko zaraz po dobraniu eksplodującego kotka.",
            "Sam potajemnie wybierasz miejsce w talii — na wierzchu, na spodzie, gdziekolwiek.",
            "Nie przekładaj ani nie podglądaj pozostałych kart.",
            "Twoja tura się kończy; nie dobierasz już karty.",
        ),
        rulebook = "Po wylosowaniu eksplodującego kotka możesz zagrać tę kartę, żeby nie " +
            "umrzeć. Następnie umieść zagraną kartę na stosie kart odrzuconych.\n\n" +
            "Potem weź eksplodującego kotka i włóż go w dowolne miejsce talii. Nie możesz " +
            "przy tym przekładać ani tasować pozostałych kart.\n\n" +
            "Chcesz dopiec kolejnemu graczowi? Umieść kotka na samym wierzchu talii. Jeśli " +
            "chcesz, możesz to zrobić pod stołem, żeby inni gracze nie widzieli, gdzie " +
            "umieszczasz kartę. Twoja tura dobiega potem końca, ale nie dobierasz karty.",
    ),
    Card.NOPE to CardText(
        name = "Nie, nie, nie",
        summary = "Anuluj właśnie zagraną kartę, jakby nigdy nie została zagrana.",
        details = listOf(
            "Możesz ją zagrać w każdej chwili, nawet gdy nie trwa Twoja tura.",
            "Nie działa na eksplodującego kotka ani na Rozbrój.",
            "Zagrana na inną kartę Nie, nie, nie przywraca działanie tamtej karty.",
        ),
        rulebook = "Anuluj działanie dowolnej karty, która nie jest eksplodującym kotkiem " +
            "ani kartą rozbrojenia. To tak, jakby każda karta (także para i wybuchowe " +
            "kombinacje) po przykryciu kartą Nie, nie, nie przestawała istnieć.\n\n" +
            "Możesz też zagrać kartę Nie, nie, nie na inną kartę Nie, nie, nie, żeby " +
            "zneutralizować jej działanie i w rezultacie stworzyć efekt „a jednak tak, " +
            "przegrańcu\" i tak dalej.\n\n" +
            "Możesz zagrać kartę Nie, nie, nie w dowolnym momencie przed rozpoczęciem " +
            "akcji, nawet jeśli nie trwa Twoja tura. Wszystkie karty, które zostały " +
            "zanienowane, wypadają z gry. Umieść je na stosie kart odrzuconych.\n\n" +
            "Możesz nawet zagrywać WYBUCHOWE KOMBINACJE!",
    ),
    Card.ATTACK to CardText(
        name = "Atakuj (2×)",
        summary = "Zakończ turę bez dobierania — następny gracz rozgrywa 2 tury.",
        details = listOf(
            "Sam w ogóle nie dobierasz karty.",
            "Ofiara rozgrywa normalną turę, a zaraz po niej kolejną.",
            "Jeśli ofiara też zagra atak, tury przechodzą na następnego gracza.",
        ),
        rulebook = "Natychmiast zakończ swoją turę (bądź tury) bez dobierania karty. " +
            "Kolejny gracz musi zagrać 2 normalne tury z rzędu (zagrać kartę albo " +
            "spasować, wziąć nową kartę, a następnie powtórzyć obie te czynności). Gra " +
            "toczy się dalej, począwszy od zaatakowanego gracza.\n\n" +
            "Jeśli ofiara ataku sama zagra jakąś kartę ataku (dowolnego typu), jej tura " +
            "natychmiast się kończy i zaatakowany przez nią gracz musi rozegrać wszystkie " +
            "jej pozostałe tury plus liczbę tur z karty ataku, która właśnie została " +
            "zagrana. Na przykład rozgrywa najpierw 4 tury, potem 6 tur i tak dalej.",
    ),
    Card.SKIP to CardText(
        name = "Pomiń",
        summary = "Natychmiast zakończ turę bez dobierania karty.",
        details = listOf(
            "Bezpieczne wyjście, gdy podejrzewasz kotka na wierzchu talii.",
            "Przeciw atakowi kończy tylko 1 z 2 tur — potrzebujesz dwóch takich kart.",
        ),
        rulebook = "Natychmiast zakończ swoją turę bez dobierania karty.\n\n" +
            "Karta pominięcia zagrana w odpowiedzi na kartę ataku kończy tylko 1 z 2 tur. " +
            "Dopiero używając 2 kart pominięcia, możesz zakończyć obie tury.",
    ),
    Card.FAVOR to CardText(
        name = "Przysługa",
        summary = "Wskaż gracza — musi dać Ci jedną kartę.",
        details = listOf(
            "To on decyduje, którą kartę dostaniesz, nie Ty.",
            "Twoja tura trwa dalej; na koniec i tak musisz dobrać kartę.",
        ),
        rulebook = "Zmuś dowolnego gracza do przekazania Ci 1 karty ze swojej ręki. To on " +
            "decyduje, którą kartę Ci odda.",
    ),
    Card.SHUFFLE to CardText(
        name = "Potasuj",
        summary = "Potasuj talię.",
        details = listOf(
            "Przydaje się, gdy wiesz, że kotek czai się na wierzchu.",
            "Twoja tura trwa dalej; na koniec i tak musisz dobrać kartę.",
        ),
        rulebook = "Tasuj talię, nie patrząc na jej zawartość. (To może się przydać " +
            "osobie, która wie, gdzie schował się eksplodujący kotek).",
    ),
    Card.SEE_THE_FUTURE to CardText(
        name = "Co kryje przyszłość (3×)",
        summary = "Podejrzyj potajemnie 3 wierzchnie karty talii.",
        details = listOf(
            "Odłóż je dokładnie w tej samej kolejności.",
            "Nie pokazuj ich nikomu.",
            "Twoja tura trwa dalej; na koniec i tak musisz dobrać kartę.",
        ),
        rulebook = "Podejrzyj 3 wierzchnie karty talii, a następnie odłóż je w tej samej " +
            "kolejności. Nie pokazuj tych kart innym graczom.",
    ),
    Card.CAT_CARD to CardText(
        name = "Kocie karty",
        summary = "Same nic nie robią. Dwie takie same kradną losową kartę.",
        details = listOf(
            "Każda karta z samym kotem i bez instrukcji to kocia karta.",
            "Dwie takie same: ukradnij losową kartę wybranemu graczowi.",
            "Trzy takie same: powiedz, jakiej karty chcesz — jeśli ją ma, musi Ci ją oddać.",
            "Pięć różnych kart: weź dowolną kartę ze stosu kart odrzuconych.",
            "Kombinacje działają z każdymi jednakowymi kartami, nie tylko z kotami.",
        ),
        rulebook = "Te karty nie działają w pojedynkę, ale jeśli uda Ci się zdobyć 2 takie " +
            "same, to możesz ich użyć jako pary. Pozwala Ci ona ukraść losową kartę " +
            "wybranemu graczowi. Możesz też używać ich w wybuchowych kombinacjach.\n\n" +
            "JAK DWIE KROPLE WODY\nTeraz możesz stworzyć parę (która pozwala Ci ukraść " +
            "losową kartę wybranemu graczowi), nie tylko zagrywając identyczne kocie " +
            "karty, ale zagrywając DOWOLNE 2 karty o tej samej nazwie (np. 2 karty " +
            "tasowania albo 2 karty pomijania). Gdy zagrywasz wybuchowe kombinacje, " +
            "ignoruj polecenia zapisane na użytych kartach.\n\n" +
            "JAK TRZY KROPLE WODY\nTa kombinacja działa tak samo jak poprzednia, ale tym " +
            "razem musisz zagrać 3 takie same karty i możesz dokładnie sprecyzować, jaką " +
            "kartę chcesz dostać od wybranego gracza. Jeśli ją ma, musi Ci ją oddać. Jeśli " +
            "jej nie ma – cóż, odchodzisz z pustymi rękami.\n\n" +
            "PIĘĆ RÓŻNYCH KART\nPo zagraniu 5 różnych kart (5 kart o różnych nazwach) " +
            "przejrzyj stos kart odrzuconych i zabierz dowolną kartę.",
    ),
)
