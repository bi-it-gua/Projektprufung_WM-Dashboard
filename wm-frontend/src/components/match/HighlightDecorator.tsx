import { MatchDisplayProps } from "./MatchDisplay";
import { StatisticsDecorator } from "./StatisticDecorator";

export function HighlightDecorator({ match, homeTeam, awayTeam }: MatchDisplayProps) {
  const diff = Math.abs((match.homeGoals ?? 0) - (match.awayGoals ?? 0));
  const isTopGame = match.homeGoals !== null && diff === 0;
  const isSurprise = match.homeGoals !== null && diff >= 3;

  return (
    <div>
      <StatisticsDecorator match={match} homeTeam={homeTeam} awayTeam={awayTeam} />
      {isTopGame && <span style={{ color: "gold" }}>⭐ Top-Spiel</span>}
      {isSurprise && <span style={{ color: "red" }}>⚡ Überraschung</span>}
    </div>
  );
}