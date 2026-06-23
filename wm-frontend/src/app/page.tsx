export default function Home() {
  return (
    <div>
      <h1>WM-Dashboard 2026</h1>
      <div className="card">
        <p>
          Willkommen beim WM-Dashboard. Dies ist die Ausgangslage f&uuml;r das
          Projekt der Module 347 und 426. Die Daten basieren auf der
          Fussball-WM 2026 (Gruppen A&ndash;D).
        </p>
        <p>Verf&uuml;gbare Ansichten:</p>
        <ul>
          <li>
            <strong>Spiele</strong> &ndash; &Uuml;bersicht aller Begegnungen
          </li>
          <li>
            <strong>Tabelle</strong> &ndash; Gruppentabellen (aktuell nur nach
            Punkten sortiert)
          </li>
          <li>
            <strong>Ergebnis erfassen</strong> &ndash; einfache Eingabe von
            Resultaten
          </li>
        </ul>
        <p>
          Diese Anwendung ist bewusst minimal gehalten. Erweiterungen erfolgen
          &uuml;ber die Issues im Projekt-Backlog.
        </p>
      </div>
    </div>
  );
}
