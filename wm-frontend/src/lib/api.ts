// Einfache API-Anbindung an das Spring-Boot-Backend.
// Die Basis-URL kann ueber NEXT_PUBLIC_API_URL gesetzt werden.

const API_URL_SERVER =
  process.env.NEXT_PUBLIC_API_URL_SERVER ?? "http://backend:8080";
const API_URL_CLIENT =
  process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";

export const API_URL = typeof window === "undefined" ? API_URL_SERVER : API_URL_CLIENT;

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
  matchType: "GROUP" | "KNOCKOUT";  // ← NEUE ZEILE
  homeGoals: number | null;
  awayGoals: number | null;
  matchday: number | null;
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
  fairPlayPoints: number;
}

export type SortStrategy = "points" | "goaldifference" | "fairplay";

export async function getTeams(): Promise<Team[]> {
  const res = await fetch(`${API_URL}/api/teams`, { cache: "no-store" });
  if (!res.ok) throw new Error("Teams konnten nicht geladen werden");
  return res.json();
}

export async function getMatches(group?: string, matchday?: number): Promise<Match[]> {
  const params = new URLSearchParams();
  if (group) params.set("group", group);
  if (matchday) params.set("matchday", String(matchday));
  const query = params.toString();
  const res = await fetch(`${API_URL}/api/matches${query ? `?${query}` : ""}`, {
    cache: "no-store",
  });
  if (!res.ok) throw new Error("Spiele konnten nicht geladen werden");
  return res.json();
}

export async function getStandings(
  group: string,
  sort: SortStrategy = "points"
): Promise<TableRow[]> {
  const res = await fetch(`${API_URL}/api/standings/${group}?sort=${sort}`, {
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
  if (!res.ok) {
    const msg = await res.text().catch(() => "");
    throw new Error(msg || "Ergebnis konnte nicht gespeichert werden");
  }
  return res.json();
}