
# 📚 **Gestion de Bibliothèque : VISIONLIB - Application Web**

## 💡 **Description**

Ce projet est une application web de gestion de bibliothèque qui permet :

- **Aux utilisateurs** de consulter et emprunter des livres 📖.
- **Aux bibliothécaires** d'ajouter, modifier ou supprimer des livres, ainsi que de gérer les utilisateurs et les emprunts 🔧.

Le projet est développé avec une architecture moderne comprenant un frontend **Angular** et un backend **Spring Boot** 🚀.

## ⚙️ **Fonctionnalités Principales**

### 🧑‍🤝‍🧑 **Utilisateur :**

- Consulter la liste des livres disponibles 📜.
- Effectuer des emprunts de livres 📚.
- Voir l’historique de ses emprunts 📅.

### 👨‍💼 **Bibliothécaire :**

- Ajouter, modifier et supprimer des livres ✍️📚.
- Gérer les utilisateurs 🧑‍💻.
- Voir et gérer les emprunts en cours et passés 🔄.

## 🛠️ **Technologies Utilisées**

### 🖥️ **Frontend :**

- **Angular** : Framework JavaScript pour la création d’interfaces utilisateur 🌐.
- **Bootstrap** : Pour un design moderne et adaptatif 🎨.

### 🖥️ **Backend :**

- **Spring Boot** : Framework Java pour le développement de l’API REST 🔌.
- **Spring Data JPA** : Gestion des entités et accès à la base de données 🔍.
- **MySQL** : Base de données relationnelle pour le stockage des informations 🗄️.

### 🔧 **Outils :**

- **Maven** : Gestionnaire de dépendances pour le backend 🔗.
- **IntelliJ IDEA** : IDE pour le développement backend 💻.
- **Visual Studio Code** : IDE pour le développement frontend 💻.
- **Postman** : Test des API REST 🧪.
- **Selenium WebDriver** : Tests automatisés pour la navigation sur l’application 🧑‍💻.

## 📝 **Installation**

### ⚡ **Prérequis :**

- Node.js et Angular CLI installés sur votre machine 🌐.
- Java JDK 17 ou supérieur ☕.
- MySQL Server configuré 🗄️.
- Maven installé 🛠️.

### 🔧 **Instructions :**

#### **Frontend :**

1. Naviguez dans le dossier `frontend`.
2. Installez les dépendances :
   ```bash
   npm install
   ```
3. Lancez le serveur de développement :
   ```bash
   ng serve
   ```
4. Accédez à l’application via [http://localhost:4200](http://localhost:4200).

#### **Backend :**

1. Naviguez dans le dossier `backend`.
2. Configurez le fichier `application.properties` avec vos informations MySQL :
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bibliotheque
   spring.datasource.username=USERNAME
   spring.datasource.password=PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Lancez l’application backend :
   ```bash
   mvn spring-boot:run
   ```

## 📁 **Structure du Projet**

### **Frontend**

- `src/app/components` : Contient les composants Angular.
- `src/app/services` : Contient les services pour l’intéraction avec l'API REST.
- `src/assets` : Contient les ressources statiques (images, CSS).

### **Backend**

- `src/main/java/com/example/demo` : Contient le code source principal.
  - `controller` : Contrôleurs REST.
  - `service` : Logique métier.
  - `repository` : Interfaces pour la communication avec la base de données.
  - `entity` : Classes représentant les tables de la base de données.
- `src/test/java/com/example/demo` : Contient les tests unitaires et d’intégration.

## 🧪 **Tests**

### **Frontend :**

- Tests unitaires avec **Jasmine/Karma**.
- Commande pour exécuter les tests :
   ```bash
   ng test
   ```

### **Backend :**

- Tests unitaires et d’intégration avec **JUnit**.
- Commande pour exécuter les tests :
   ```bash
   mvn test
   ```

### **Tests End-to-End :**

- Utilisation de **Selenium WebDriver** pour valider les fonctionnalités principales.
- Les tests sont situés dans `src/test/java/com/example/demo/tests`.

## 👤 **Auteur**

- **Rim Belabadia**
- Contact : [rimbelabadia1234@gmail.com](mailto:rimbelabadia1234@gmail.com)

## 🔮 **Améliorations Futures**

- 🌟 Intégration d’une fonctionnalité de recherche avancée.
- 🛎️ Ajout d’un système de notification pour les retards d’emprunts.
- 📊 Implémentation d’un tableau de bord pour les statistiques de gestion.

---

Cela ajoute une touche dynamique à votre rapport tout en gardant une structure claire et professionnelle.
