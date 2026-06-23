import { getMatches, getTeams, Match, Team } from "@/lib/api";

function teamName(teams: Team[], id: number): string {
  return teams.find((t) => t.id === id)?.name ?? `Team ${id}`;
}

export default async function MatchesPage() {
  let matches: Match[] = [];
  let teams: Team[] = [];
  let error: string | null = null;

  try {
    [matches, teams] = await Promise.all([getMatches(), getTeams()]);
  } catch {
    error = "Backend nicht erreichbar. Laeuft das Spring-Boot-Backend?";
  }

  if (error) {
    return (
      <div>
        <h1>Spiele</h1>
        <div className="card">{error}</div>
      </div>
    );
  }

  return (
    <div>
      <h1>Spiele</h1>
      <table>
        <thead>
          <tr>
            <th>Gruppe</th>
            <th>Heim</th>
            <th>Gast</th>
            <th>Ergebnis</th>
          </tr>
        </thead>
        <tbody>
          {matches.map((m) => (
            <tr key={m.id}>
              <td>
                <span className="group-badge">{m.group}</span>
              </td>
              <td>{teamName(teams, m.homeTeamId)}</td>
              <td>{teamName(teams, m.awayTeamId)}</td>
              <td>
                {m.homeGoals !== null && m.awayGoals !== null ? (
                  <span className="score">
                    {m.homeGoals} : {m.awayGoals}
                  </span>
                ) : (
                  <span className="score open">offen</span>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
