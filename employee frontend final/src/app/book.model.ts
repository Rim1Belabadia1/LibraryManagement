// src/app/book.model.ts
export interface Book {
  id?: number; // Utilisé comme champ optionnel lors de la création d'un nouveau livre
  name: string;
  type: string;
  description?: string; // Champ optionnel
  editeur: string;
  auteur: string;
  dateDePublication: string; // Date au format ISO 8601
  photoBase64?: string; // Champ optionnel pour la photo
}
