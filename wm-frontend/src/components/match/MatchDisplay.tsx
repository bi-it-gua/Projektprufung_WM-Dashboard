import { Match } from "@/lib/api";

export interface MatchDisplayProps {
  match: Match;
  homeTeam: string;
  awayTeam: string;
}

export function BasicMatchDisplay({ match, homeTeam, awayTeam }: MatchDisplayProps) {
  return (
    <div className="match-card">
      <span>{homeTeam}</span>
      <span className="score">
        {match.homeGoals !== null && match.awayGoals !== null
          ? `${match.homeGoals} : ${match.awayGoals}`
          : "offen"}
      </span>
      <span>{awayTeam}</span>
    </div>
  );
}