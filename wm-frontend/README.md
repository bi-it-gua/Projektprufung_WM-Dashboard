# WM-Dashboard – Frontend (Ausgangslage)

Next.js-Frontend (App Router, TypeScript) für das WM-Dashboard. Dies ist die
**Ausgangslage** für das Projekt der Module 347 und 426.

## Voraussetzungen

- Node.js 18.18+ (empfohlen: Node 20 oder 22)
- Das Backend muss laufen (Standard: `http://localhost:8080`)

## Installieren & Starten

```bash
npm install
npm run dev
```

Das Frontend läuft danach auf `http://localhost:3000`.

Die Backend-URL kann über eine Datei `.env.local` gesetzt werden
(siehe `.env.local.example`):

```
NEXT_PUBLIC_API_URL=http://localhost:8080
```

## Seiten

- `/` – Startseite
- `/matches` – Übersicht aller Spiele
- `/standings` – Gruppentabellen (A und B)
- `/admin` – einfache Ergebniseingabe

## Bewusst eingebaute Lücken (Ausgangslage)

- **Karges Design:** nur minimales CSS.
- **Tabelle ohne Tiebreaker:** zeigt die Reihenfolge, die das Backend liefert
  (nur nach Punkten).
- **Keine Eingabevalidierung:** im Formular unter `/admin` werden die Werte
  ungeprüft an das Backend geschickt.
- **Keine Containerisierung:** es ist kein Dockerfile vorhanden.

## Struktur

```
src/
  app/
    layout.tsx        Navigation + Grundgerüst
    page.tsx          Startseite
    matches/page.tsx  Spiele (Server Component)
    standings/page.tsx Tabelle (Server Component)
    admin/page.tsx    Ergebniseingabe (Client Component)
  lib/
    api.ts            API-Anbindung + Typen
```
