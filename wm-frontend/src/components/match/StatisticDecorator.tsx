import { MatchDisplayProps, BasicMatchDisplay } from "./MatchDisplay";

export function StatisticsDecorator({ match, homeTeam, awayTeam }: MatchDisplayProps) {
  const diff = (match.homeGoals ?? 0) - (match.awayGoals ?? 0);

  return (
    <div>
      <BasicMatchDisplay match={match} homeTeam={homeTeam} awayTeam={awayTeam} />
      {match.homeGoals !== null && (
        <div style={{ fontSize: "0.8rem", color: "gray" }}>
          Tordifferenz: {diff > 0 ? `+${diff}` : diff}
        </div>
      )}
    </div>
  );
}