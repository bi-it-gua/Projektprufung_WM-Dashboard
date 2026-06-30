"use client";

import { useRouter, useSearchParams } from "next/navigation";

const GROUPS = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"];
const MATCHDAYS = [1, 2, 3];

export default function MatchFilter() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const group = searchParams.get("group") ?? "";
  const matchday = searchParams.get("matchday") ?? "";

  function update(next: { group?: string; matchday?: string }) {
    const params = new URLSearchParams(searchParams.toString());
    const merged = { group, matchday, ...next };
    if (merged.group) params.set("group", merged.group);
    else params.delete("group");
    if (merged.matchday) params.set("matchday", merged.matchday);
    else params.delete("matchday");
    const query = params.toString();
    router.push(`/matches${query ? `?${query}` : ""}`);
  }

  return (
    <div style={{ display: "flex", gap: "1rem", marginBottom: "1rem" }}>
      <label>
        Gruppe:{" "}
        <select value={group} onChange={(e) => update({ group: e.target.value })}>
          <option value="">Alle</option>
          {GROUPS.map((g) => (
            <option key={g} value={g}>
              {g}
            </option>
          ))}
        </select>
      </label>
      <label>
        Spieltag:{" "}
        <select value={matchday} onChange={(e) => update({ matchday: e.target.value })}>
          <option value="">Alle</option>
          {MATCHDAYS.map((md) => (
            <option key={md} value={md}>
              {md}
            </option>
          ))}
        </select>
      </label>
    </div>
  );
}
