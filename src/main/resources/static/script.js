let aktuelleStimmung = null;
let aktuellerNutzer = null;

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
            aktuellerNutzer = nutzer;
            document.getElementById('begruessung').textContent =
                'Hey ' + nutzer.name + ', wie war dein Tag?';
            document.getElementById('datum-input').value = new Date().toISOString().split('T')[0];
            document.getElementById('setup-screen').classList.add('hidden');
            document.getElementById('eintrag-screen').classList.remove('hidden');
        });
}

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

    const bohnenTexte = Array.from(document.querySelectorAll('.bohne-input'))
        .map(input => input.value)
        .filter(text => text.trim() !== '');

    if (bohnenTexte.length === 0) {
        alert('Bitte mindestens eine Bohne eintragen.');
        return;
    }

    const datum = document.getElementById('datum-input').value;

    if (!datum) {
        alert('Bitte ein Datum auswählen.');
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
            console.log(gespeichert);
        });
}