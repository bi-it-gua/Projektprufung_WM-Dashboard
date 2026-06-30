"use client";

import { useRouter, useSearchParams } from "next/navigation";
import { SortStrategy } from "@/lib/api";

const OPTIONS: { value: SortStrategy; label: string }[] = [
  { value: "points", label: "Punkte" },
  { value: "goaldifference", label: "Tordifferenz" },
  { value: "fairplay", label: "Fair-Play" },
];

export default function SortSelector() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const current = searchParams.get("sort") ?? "points";

  function onChange(value: string) {
    router.push(`/standings?sort=${value}`);
  }

  return (
    <label>
      Wertungslogik:{" "}
      <select value={current} onChange={(e) => onChange(e.target.value)}>
        {OPTIONS.map((o) => (
          <option key={o.value} value={o.value}>
            {o.label}
          </option>
        ))}
      </select>
    </label>
  );
}
