import { getStandings, TableRow } from "@/lib/api";

const GROUPS = ["A", "B", "C", "D"];

async function GroupTable({ group }: { group: string }) {
  let rows: TableRow[] = [];
  try {
    rows = await getStandings(group);
  } catch {
    return (
      <div className="card">
        Tabelle f&uuml;r Gruppe {group} nicht verf&uuml;gbar.
      </div>
    );
  }

  return (
    <div className="card">
      <h2>
        <span className="group-badge">{group}</span> Gruppe {group}
      </h2>
      <table>
        <thead>
          <tr>
            <th>Team</th>
            <th>Sp</th>
            <th>S</th>
            <th>U</th>
            <th>N</th>
            <th>Tore</th>
            <th>Diff</th>
            <th>Pkt</th>
          </tr>
        </thead>
        <tbody>
          {rows.map((r) => (
            <tr key={r.teamId}>
              <td>{r.teamName}</td>
              <td>{r.played}</td>
              <td>{r.won}</td>
              <td>{r.drawn}</td>
              <td>{r.lost}</td>
              <td>
                {r.goalsFor}:{r.goalsAgainst}
              </td>
              <td>{r.goalDifference}</td>
              <td>
                <strong>{r.points}</strong>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default function StandingsPage() {
  return (
    <div>
      <h1>Tabelle</h1>
      <p>
        Hinweis: Die Tabelle ist aktuell nur nach Punkten sortiert. Bei
        Punktgleichheit fehlt eine sinnvolle Reihenfolge.
      </p>
      {GROUPS.map((g) => (
        <GroupTable key={g} group={g} />
      ))}
    </div>
  );
}
