import { Match } from "@/lib/api";

interface MatchCardProps {
  match: Match;
  homeTeam: string;
  awayTeam: string;
}

// Tip 1: Grup maçı
function GroupMatchCard({ match, homeTeam, awayTeam }: MatchCardProps) {
  return (
    <span style={{ fontSize: "0.75rem", background: "#e0f0ff", padding: "2px 6px", borderRadius: "4px" }}>
      Gruppenspiel
    </span>
  );
}

// Tip 2: K.o. maçı
function KnockoutMatchCard({ match, homeTeam, awayTeam }: MatchCardProps) {
  return (
    <span style={{ fontSize: "0.75rem", background: "#ffe0b2", padding: "2px 6px", borderRadius: "4px" }}>
      ⏱ K.o.-Spiel
    </span>
  );
}

export function createMatchCard(match: Match, homeTeam: string, awayTeam: string) {
  if (match.matchType === "KNOCKOUT") {
    return <KnockoutMatchCard match={match} homeTeam={homeTeam} awayTeam={awayTeam} />;
  }
  return <GroupMatchCard match={match} homeTeam={homeTeam} awayTeam={awayTeam} />;
}