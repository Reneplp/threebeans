let aktuelleStimmung = null;
let aktuellerNutzer = null;

const STIMMUNG_EMOJI = {
    HERVORRAGEND: '😄',
    GUT: '🙂',
    NAJA: '😐',
    SCHLECHT: '🙁'
};

// ---------- Screen-Wechsel ----------

function alleScreensVerstecken() {
    document.querySelectorAll('.screen').forEach(s => s.classList.add('hidden'));
}

function zeigeAuswahl() {
    alleScreensVerstecken();
    document.getElementById('auswahl-screen').classList.remove('hidden');
    ladeNutzerListe();
}

function zeigeSetup() {
    alleScreensVerstecken();
    document.getElementById('setup-screen').classList.remove('hidden');
}

function zeigeEintrag() {
    alleScreensVerstecken();
    document.getElementById('eintrag-screen').classList.remove('hidden');
}

function zeigeHistorie() {
    alleScreensVerstecken();
    document.getElementById('historie-screen').classList.remove('hidden');
    document.getElementById('historie-titel').textContent = 'Historie von ' + aktuellerNutzer.name;
    ladeHistorieListe();
}

// ---------- Nutzer-Auswahl ----------

function ladeNutzerListe() {
    fetch('/api/nutzer')
        .then(response => response.json())
        .then(nutzerListe => {
            const container = document.getElementById('nutzer-liste');
            container.innerHTML = '';
            nutzerListe.forEach(nutzer => {
                const btn = document.createElement('button');
                btn.textContent = nutzer.name;
                btn.onclick = () => nutzerWaehlen(nutzer);
                container.appendChild(btn);
            });
        });
}

function nutzerWaehlen(nutzer) {
    aktuellerNutzer = nutzer;
    document.getElementById('begruessung').textContent =
        'Hey ' + nutzer.name + ', wie war dein Tag?';
    document.getElementById('datum-input').value = new Date().toISOString().split('T')[0];
    zeigeEintrag();
}

function nutzerAnlegen() {
    const name = document.getElementById('name-input').value;
    const alter = document.getElementById('alter-input').value;

    if (!name || !alter) {
        alert('Bitte Name und Alter eingeben.');
        return;
    }

    fetch('/api/nutzer', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: name, alter: parseInt(alter) })
    })
        .then(response => response.json())
        .then(nutzer => {
            nutzerWaehlen(nutzer);
        });
}

// ---------- Eintragen ----------

function stimmungWaehlen(stimmung) {
    aktuelleStimmung = stimmung;
    document.querySelectorAll('#stimmung-auswahl button').forEach(btn => {
        btn.classList.toggle('aktiv', btn.dataset.stimmung === stimmung);
    });
}

function bohneHinzufuegen() {
    const liste = document.getElementById('bohnen-liste');
    const zeile = document.createElement('div');
    zeile.className = 'bohne-zeile';
    zeile.innerHTML = `
        <input type="text" class="bohne-input" placeholder="Was war heute toll?">
        <button onclick="bohneEntfernen(this)">X</button>
    `;
    liste.appendChild(zeile);
}

function bohneEntfernen(button) {
    button.parentElement.remove();
}

function eintragSpeichern() {
    if (!aktuelleStimmung) {
        alert('Bitte eine Stimmung auswählen.');
        return;
    }

    const datum = document.getElementById('datum-input').value;
    if (!datum) {
        alert('Bitte ein Datum auswählen.');
        return;
    }

    const bohnenTexte = Array.from(document.querySelectorAll('.bohne-input'))
        .map(input => input.value)
        .filter(text => text.trim() !== '');

    if (bohnenTexte.length === 0) {
        alert('Bitte mindestens eine Bohne eintragen.');
        return;
    }

    const tageseintrag = {
        datum: datum,
        stimmung: aktuelleStimmung,
        nutzer: { id: aktuellerNutzer.id },
        bohnen: bohnenTexte.map(text => ({ text: text }))
    };

    fetch('/api/tageseintraege', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(tageseintrag)
    })
        .then(response => response.json())
        .then(gespeichert => {
            alert('Gespeichert!');
        });
}

// ---------- Historie ----------

function ladeHistorieListe() {
    fetch('/api/tageseintraege')
        .then(response => response.json())
        .then(alleEintraege => {
            const eigene = alleEintraege
                .filter(e => e.nutzer && e.nutzer.id === aktuellerNutzer.id)
                .sort((a, b) => b.datum.localeCompare(a.datum));

            const container = document.getElementById('historie-liste');
            container.innerHTML = '';

            if (eigene.length === 0) {
                container.textContent = 'Noch keine Einträge vorhanden.';
                return;
            }

            eigene.forEach(eintrag => {
                container.appendChild(erstelleEintragsKarte(eintrag));
            });
        });
}

function erstelleEintragsKarte(eintrag) {
    const div = document.createElement('div');
    div.className = 'historie-eintrag';

    const kopf = document.createElement('div');
    kopf.className = 'datum-stimmung';
    kopf.textContent = eintrag.datum + ' ' + (STIMMUNG_EMOJI[eintrag.stimmung] || '');
    div.appendChild(kopf);

    const liste = document.createElement('ul');
    eintrag.bohnen.forEach(bohne => {
        const li = document.createElement('li');
        li.textContent = bohne.text;
        liste.appendChild(li);
    });
    div.appendChild(liste);

    return div;
}

function zeigeTag() {
    const datum = document.getElementById('historie-datum').value;
    const ansicht = document.getElementById('historie-tag-ansicht');

    if (!datum) {
        ansicht.innerHTML = '';
        return;
    }

    fetch('/api/tageseintraege')
        .then(response => response.json())
        .then(alleEintraege => {
            const treffer = alleEintraege.find(e =>
                e.nutzer && e.nutzer.id === aktuellerNutzer.id && e.datum === datum
            );

            ansicht.innerHTML = '';
            if (!treffer) {
                ansicht.textContent = 'Kein Eintrag für diesen Tag.';
                return;
            }
            ansicht.appendChild(erstelleEintragsKarte(treffer));
        });
}

// ---------- Start ----------

zeigeAuswahl();