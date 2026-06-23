import type { Metadata } from "next";
import Link from "next/link";
import "./globals.css";

export const metadata: Metadata = {
  title: "WM-Dashboard",
  description: "Ausgangslage Module 347 / 426",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="de">
      <body>
        <nav>
          <span className="brand">⚽ WM-Dashboard 2026</span>
          <Link href="/">Start</Link>
          <Link href="/matches">Spiele</Link>
          <Link href="/standings">Tabelle</Link>
          <Link href="/admin">Ergebnis erfassen</Link>
        </nav>
        <main>{children}</main>
      </body>
    </html>
  );
}
