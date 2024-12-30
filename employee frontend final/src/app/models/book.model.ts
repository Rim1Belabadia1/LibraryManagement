export interface Book {
  id: number;
  title: string;
  publisher: string;
  publicationYear: number;
  isbn: string;
  genre: string;
  language: string;
  pageCount: number;
  summary: string;
  acquisitionDate: string; // Changer le type en string
  condition: string;
  availability: boolean;
  location: string;
  keywords: string;
}
