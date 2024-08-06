import { Inter } from "next/font/google";
import Head from "../../../components/Header/SearchResultsHeader"

const inter = Inter({ subsets: ["latin"] });


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <Head/>
        {children}
      </body>
    </html>
  );
}