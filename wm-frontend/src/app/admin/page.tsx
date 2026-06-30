"use client";

import { useEffect, useState } from "react";
import { getMatches, getTeams, updateResult, Match, Team } from "@/lib/api";

export default function AdminPage() {
  const [matches, setMatches] = useState<Match[]>([]);
  const [teams, setTeams] = useState<Team[]>([]);
  const [status, setStatus] = useState<string>("");

  async function load() {
    try {
      const [m, t] = await Promise.all([getMatches(), getTeams()]);
      setMatches(m);
      setTeams(t);
    } catch {
      setStatus("Backend nicht erreichbar.");
    }
  }

  useEffect(() => {
    load();
  }, []);

  function teamName(id: number): string {
    return teams.find((t) => t.id === id)?.name ?? `Team ${id}`;
  }

  async function handleSave(matchId: number, home: string, away: string) {
    const h = Number(home);
    const a = Number(away);
    if (home === "" || away === "") {
      setStatus("error:Bitte beide Toranzahlen eingeben.");
      return;
    }
    if (!Number.isInteger(h) || !Number.isInteger(a)) {
      setStatus("error:Tore müssen ganze Zahlen sein.");
      return;
    }
    if (h < 0 || a < 0) {
      setStatus("error:Tore dürfen nicht negativ sein.");
      return;
    }
    setStatus("");
    try {
      await updateResult(matchId, h, a);
      setStatus("ok:Ergebnis gespeichert.");
      await load();
    } catch (e: unknown) {
      setStatus("error:" + (e instanceof Error ? e.message : "Speichern fehlgeschlagen."));
    }
  }

  return (
    <div>
      <h1>Ergebnis erfassen</h1>
      {status && (
        <p className={status.startsWith("error:") ? "status error" : "status ok"}>
          {status.startsWith("error:") ? "⚠ " : "✓ "}
          {status.replace(/^(error|ok):/, "")}
        </p>
      )}
      <table>
        <thead>
          <tr>
            <th>Gruppe</th>
            <th>Heim</th>
            <th>Gast</th>
            <th>Heim-Tore</th>
            <th>Gast-Tore</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {matches.map((m) => (
            <ResultRow
              key={m.id}
              match={m}
              homeName={teamName(m.homeTeamId)}
              awayName={teamName(m.awayTeamId)}
              onSave={handleSave}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
}

function ResultRow({
  match,
  homeName,
  awayName,
  onSave,
}: {
  match: Match;
  homeName: string;
  awayName: string;
  onSave: (id: number, home: string, away: string) => void;
}) {
  const [home, setHome] = useState<string>(
    match.homeGoals !== null ? String(match.homeGoals) : ""
  );
  const [away, setAway] = useState<string>(
    match.awayGoals !== null ? String(match.awayGoals) : ""
  );

  return (
    <tr>
      <td>
        <span className="group-badge">{match.group}</span>
      </td>
      <td>{homeName}</td>
      <td>{awayName}</td>
      <td>
        <input
          type="number"
          value={home}
          onChange={(e) => setHome(e.target.value)}
        />
      </td>
      <td>
        <input
          type="number"
          value={away}
          onChange={(e) => setAway(e.target.value)}
        />
      </td>
      <td>
        <button onClick={() => onSave(match.id, home, away)}>Speichern</button>
      </td>
    </tr>
  );
}
