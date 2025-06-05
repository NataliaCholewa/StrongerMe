# STRONGERME

**StrongerMe** to aplikacja webowa stworzona z myślą o osobach chcących śledzić swoje postępy treningowe, monitorować liczbę wykonanych ćwiczeń i planować cele tygodniowe. Umożliwia tworzenie, edycję i przegląd planów treningowych oraz ich analizę.

## 📐 Architektura

Projekt oparty jest na architekturze warstwowej (layered architecture):

- **Frontend (React + CSS)** – interfejs użytkownika dostępny przez przeglądarkę.

- **Backend (Spring Boot)** – serwer aplikacji, logika biznesowa, zabezpieczenia (JWT), komunikacja z bazą.

- **PostgreSQL** – trwała baza danych z relacjami między użytkownikiem, ćwiczeniami i treningami.

## 🚀 Jak uruchomić?

### Backend

1. Przejdź do katalogu `backend`:

   cd backend
   
   mvn spring-boot:run

## Fronend

1. Przejdź do katalogu `react-app`:
   
   cd react-app

2. Zainstaluj zależności:

    npm install

3. Uruchom frontend:
    npm run dev



🛠️ Technologie:

Java 24	-	Stabilna wersja 
Spring Boot	- Serwer REST API	Popularny framework, szybki start, integracja z JPA
Spring Security + JWT - Autoryzacja	Umożliwia bezpieczne logowanie i zarządzanie sesją
PostgreSQL- Wydajna, relacyjna baza z dobrym wsparciem w Spring
React - Nowoczesna biblioteka do budowy UI
CSS	- Łatwe stylowanie, responsywność
JUnit / Mockito	- Popularne, sprawdzone biblioteki

🔐 Funkcje:

Rejestracja i logowanie z JWT

Tworzenie i edycja treningów

Przegląd historii i szczegółów ćwiczeń

Podsumowanie treningowe (liczba, objętość, cel tygodniowy)

Profil użytkownika z podstawowymi danymi

📦 Autorzy:

Natalia Cholewa