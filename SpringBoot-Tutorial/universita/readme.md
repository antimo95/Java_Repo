# 🎓 Università API

API REST per la gestione di una università, sviluppata con **Spring Boot** come esercizio pratico di consolidamento del framework.

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

## 📦 Entità e Relazioni

### Corso
| Campo | Tipo | Vincoli |
|---|---|---|
| id | Long | generato automaticamente |
| nome | String | obbligatorio |
| postiMassimi | int | numero positivo |
| studenti | List\<Studente\> | relazione `@OneToMany` |

### Studente
| Campo | Tipo | Vincoli |
|---|---|---|
| id | Long | generato automaticamente |
| nome | String | obbligatorio |
| cognome | String | obbligatorio |
| email | String | obbligatorio, formato valido |
| corso | Corso | relazione `@ManyToOne` |

### Relazione tra entità

Un **Corso** può avere molti **Studenti**, ogni **Studente** è iscritto a un solo **Corso** (relazione `OneToMany` / `ManyToOne`).

```
Corso (1) ────────── (N) Studente
```

## 🔗 Endpoints

### Corsi
| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/corsi` | Restituisce tutti i corsi con gli studenti iscritti |
| GET | `/corsi/{id}` | Restituisce un corso per ID |
| POST | `/corsi` | Crea un nuovo corso |
| DELETE | `/corsi/{id}` | Elimina un corso |

### Studenti
| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/studenti` | Restituisce tutti gli studenti |
| GET | `/studenti/{id}` | Restituisce uno studente per ID |
| POST | `/studenti?corsoId={id}` | Crea uno studente e lo iscrive a un corso |
| PUT | `/studenti/{id}/corso?corsoId={id}` | Cambia il corso di uno studente |
| DELETE | `/studenti/{id}` | Elimina uno studente |

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
- JDBC URL: `jdbc:h2:mem:universitadb`
- Username: `sa`
- Password: *(vuota)*

## 📬 Esempi di utilizzo

**Crea un corso:**
```json
POST /corsi
{ "nome": "Informatica", "postiMassimi": 30 }
```

**Iscrivi uno studente:**
```json
POST /studenti?corsoId=1
{ "nome": "Mario", "cognome": "Rossi", "email": "mario@email.com" }
```

**Cambia corso a uno studente:**
```
PUT /studenti/1/corso?corsoId=2
```

## 📖 Concetti applicati

| Concetto | Descrizione |
|---|---|
| `@RestController` | gestisce richieste HTTP e restituisce JSON |
| `@Service` | contiene la logica di business |
| `@Entity` / `@Table` | mappa una classe Java su una tabella del database |
| `@OneToMany` / `@ManyToOne` | relazione uno a molti tra Corso e Studente |
| `@JsonManagedReference` / `@JsonBackReference` | evita loop infiniti nella serializzazione JSON |
| `@Valid` | attiva la validazione dei dati in ingresso |
| `@PathVariable` | estrae valori dal percorso URL |
| `@RequestParam` | estrae parametri dalla query string |
