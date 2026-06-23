// Einfache API-Anbindung an das Spring-Boot-Backend.
// Die Basis-URL kann ueber NEXT_PUBLIC_API_URL gesetzt werden.

export const API_URL =
  process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";

export interface Team {
  id: number;
  name: string;
  group: string;
}

export interface Match {
  id: number;
  homeTeamId: number;
  awayTeamId: number;
  group: string;
  homeGoals: number | null;
  awayGoals: number | null;
}

export interface TableRow {
  teamId: number;
  teamName: string;
  played: number;
  won: number;
  drawn: number;
  lost: number;
  goalsFor: number;
  goalsAgainst: number;
  goalDifference: number;
  points: number;
}

export async function getTeams(): Promise<Team[]> {
  const res = await fetch(`${API_URL}/api/teams`, { cache: "no-store" });
  if (!res.ok) throw new Error("Teams konnten nicht geladen werden");
  return res.json();
}

export async function getMatches(group?: string): Promise<Match[]> {
  const url = group
    ? `${API_URL}/api/matches?group=${group}`
    : `${API_URL}/api/matches`;
  const res = await fetch(url, { cache: "no-store" });
  if (!res.ok) throw new Error("Spiele konnten nicht geladen werden");
  return res.json();
}

export async function getStandings(group: string): Promise<TableRow[]> {
  const res = await fetch(`${API_URL}/api/standings/${group}`, {
    cache: "no-store",
  });
  if (!res.ok) throw new Error("Tabelle konnte nicht geladen werden");
  return res.json();
}

export async function updateResult(
  matchId: number,
  homeGoals: number,
  awayGoals: number
): Promise<Match> {
  const res = await fetch(`${API_URL}/api/matches/${matchId}/result`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ homeGoals, awayGoals }),
  });
  if (!res.ok) throw new Error("Ergebnis konnte nicht gespeichert werden");
  return res.json();
}
