# 📚 Libreria API

API REST per la gestione di una libreria, sviluppata con **Spring Boot** come progetto di apprendimento del framework.

## 🛠️ Tecnologie utilizzate

- **Java 17**
- **Spring Boot 4.0.6**
- **Spring Data JPA** — gestione del database
- **Spring Validation** — validazione dei dati in ingresso
- **H2 Database** — database in memoria (si resetta ad ogni riavvio)
- **Hibernate** — ORM per la mappatura oggetti/tabelle

## 📐 Architettura

Il progetto segue il pattern **MVC a 3 livelli**:

```
Controller  →  riceve le richieste HTTP e restituisce JSON
Service     →  contiene la logica di business
Repository  →  comunica con il database tramite JPA
```

Il flusso completo di una richiesta:
```
HTTP Request
     ↓
Controller        riceve la richiesta, la valida (@Valid)
     ↓
Service           esegue la logica di business
     ↓
Repository        interroga il database (JPA)
     ↓
Database H2
```

## 📦 Entità e Relazioni

### Autore
| Campo | Tipo | Vincoli |
|---|---|---|
| id | Long | generato automaticamente |
| nome | String | obbligatorio |
| cognome | String | obbligatorio |
| libri | List\<Libro\> | relazione `@OneToMany` |

### Libro
| Campo | Tipo | Vincoli |
|---|---|---|
| id | Long | generato automaticamente |
| titolo | String | obbligatorio |
| anno | int | tra 1000 e 2100 |
| autore | Autore | relazione `@ManyToOne` |

### Relazione tra entità
Un **Autore** può avere scritto molti **Libri**, ogni **Libro** appartiene a un solo **Autore** (relazione `OneToMany` / `ManyToOne`).

```
Autore (1) ────────── (N) Libro
```

## 🔗 Endpoints

### Autori
| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/autori` | Restituisce tutti gli autori con i loro libri |
| GET | `/autori/{id}` | Restituisce un autore per ID |
| POST | `/autori` | Crea un nuovo autore |
| PUT | `/autori/{id}` | Aggiorna un autore esistente |
| DELETE | `/autori/{id}` | Elimina un autore e tutti i suoi libri |

### Libri
| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/libri` | Restituisce tutti i libri |
| GET | `/libri/{id}` | Restituisce un libro per ID |
| POST | `/libri?autoreId={id}` | Crea un nuovo libro associato a un autore |
| DELETE | `/libri/{id}` | Elimina un libro |

### Ricerca Libri
| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/libri/cerca?titolo={parola}` | Cerca libri per parola nel titolo (case insensitive) |
| GET | `/libri/dopo?anno={anno}` | Cerca libri pubblicati dopo un certo anno |
| GET | `/libri/autore/{autoreId}` | Cerca tutti i libri di un autore per ID |
| GET | `/libri/cognome?cognome={cognome}` | Cerca libri per cognome dell'autore |
| GET | `/libri/filtra?anno={anno}&parola={parola}` | Filtra per anno e parola nel titolo |

## 🔍 Query personalizzate

Il progetto usa tre approcci diversi per interrogare il database:

**1. Metodi standard JpaRepository** — per operazioni CRUD base:
```java
libroRepository.findAll()
libroRepository.findById(id)
libroRepository.save(libro)
libroRepository.deleteById(id)
```

**2. Query Methods** — Spring genera il SQL dal nome del metodo:
```java
findByTitoloContainingIgnoreCase(String parola)
findByAnnoGreaterThan(int anno)
findByAutoreId(Long autoreId)
```

**3. @Query con JPQL** — per query complesse che attraversano relazioni:
```java
@Query("SELECT l FROM Libro l WHERE l.autore.cognome = :cognome")
List<Libro> findLibriByAutoreCognome(@Param("cognome") String cognome);
```

## ✅ Validazione e Gestione Errori

I dati in ingresso vengono validati automaticamente con `@Valid`:
- `@NotBlank` — campo obbligatorio
- `@Min` / `@Max` — valore numerico nel range corretto
- `@Email` — formato email valido

Gli errori vengono gestiti centralmente dal `GlobalExceptionHandler` con `@RestControllerAdvice`, che restituisce risposte JSON chiare:
```json
{
    "timestamp": "2026-06-10T10:00:00",
    "status": 404,
    "errore": "Autore con id 99 non trovato"
}
```

## ▶️ Come avviare il progetto

### Prerequisiti
- Java 17+
- Maven (incluso nel progetto tramite Maven Wrapper)

### Avvio
```bash
.\mvnw.cmd spring-boot:run       # Windows
./mvnw spring-boot:run           # Mac/Linux
```

L'app parte su `http://localhost:8080`

### Console H2 (database)
Accessibile su `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:libreriadb`
- Username: `sa`
- Password: *(vuota)*

## 📬 Esempi di utilizzo

**Crea un autore:**
```json
POST /autori
{ "nome": "Umberto", "cognome": "Eco" }
```

**Aggiungi un libro:**
```json
POST /libri?autoreId=1
{ "titolo": "Il Nome della Rosa", "anno": 1980 }
```

**Cerca libri per titolo:**
```
GET /libri/cerca?titolo=rosa
```

**Cerca libri di un autore per cognome:**
```
GET /libri/cognome?cognome=Eco
```

**Filtra libri per anno e parola nel titolo:**
```
GET /libri/filtra?anno=1950&parola=barone
```

## 📖 Concetti appresi

| Concetto | Descrizione |
|---|---|
| `@RestController` | gestisce richieste HTTP e restituisce JSON |
| `@Service` | contiene la logica di business |
| `@Repository` | comunica con il database |
| `@Entity` / `@Table` | mappa una classe Java su una tabella del database |
| `@OneToMany` / `@ManyToOne` | relazioni tra entità |
| `@JsonManagedReference` / `@JsonBackReference` | evita loop infiniti nella serializzazione JSON |
| `@Valid` | attiva la validazione dei dati in ingresso |
| `@RestControllerAdvice` | gestione centralizzata degli errori |
| `@PathVariable` | estrae valori dal percorso URL (`/libri/1`) |
| `@RequestParam` | estrae parametri dalla query string (`?titolo=rosa`) |
| `@Query` + JPQL | query personalizzate sulle entità Java |
| Query Methods | query generate automaticamente dal nome del metodo |
