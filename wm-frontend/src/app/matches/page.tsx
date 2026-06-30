import { getMatches, getTeams, Match, Team } from "@/lib/api";
import { createMatchCard } from "@/components/match/MatchFactory";
import MatchFilter from "@/components/match/MatchFilter";
import { HighlightDecorator } from "@/components/match/HighlightDecorator";

function teamName(teams: Team[], id: number): string {
  return teams.find((t) => t.id === id)?.name ?? `Team ${id}`;
}

export default async function MatchesPage({
  searchParams,
}: {
  searchParams: Promise<{ group?: string; matchday?: string }>;
}) {
  const { group, matchday } = await searchParams;
  let matches: Match[] = [];
  let teams: Team[] = [];
  let error: string | null = null;

  try {
    [matches, teams] = await Promise.all([
      getMatches(group, matchday ? Number(matchday) : undefined),
      getTeams(),
    ]);
  } catch {
    error = "Backend nicht erreichbar. Laeuft das Spring-Boot-Backend?";
  }

  if (error) {
    return (
      <div>
        <h1>Spiele</h1>
        <MatchFilter />
        <div className="card">{error}</div>
      </div>
    );
  }

  return (
    <div>
      <h1>Spiele</h1>
      <MatchFilter />
      <table>
        <thead>
          <tr>
            <th>Gruppe</th>
            <th>Heim</th>
            <th>Gast</th>
            <th>Ergebnis</th>
            <th>Typ</th>
            <th>Info</th>
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
              <td>
                {createMatchCard(m, teamName(teams, m.homeTeamId), teamName(teams, m.awayTeamId))}
              </td>
              <td>
                <HighlightDecorator match={m} homeTeam={teamName(teams, m.homeTeamId)} awayTeam={teamName(teams, m.awayTeamId)} />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}