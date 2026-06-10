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

## 📦 Entità

### Autore
| Campo | Tipo | Note |
|---|---|---|
| id | Long | generato automaticamente |
| nome | String | obbligatorio |
| cognome | String | obbligatorio |
| libri | List\<Libro\> | relazione OneToMany |

### Libro
| Campo | Tipo | Note |
|---|---|---|
| id | Long | generato automaticamente |
| titolo | String | obbligatorio |
| anno | int | tra 1000 e 2100 |
| autore | Autore | relazione ManyToOne |

## 🔗 Endpoints

### Autori
| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/autori` | Restituisce tutti gli autori |
| GET | `/autori/{id}` | Restituisce un autore per ID |
| POST | `/autori` | Crea un nuovo autore |
| PUT | `/autori/{id}` | Aggiorna un autore esistente |
| DELETE | `/autori/{id}` | Elimina un autore |

### Libri
| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/libri` | Restituisce tutti i libri |
| GET | `/libri/{id}` | Restituisce un libro per ID |
| POST | `/libri?autoreId={id}` | Crea un nuovo libro associato a un autore |
| DELETE | `/libri/{id}` | Elimina un libro |

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

## 📬 Esempio di utilizzo

**Crea un autore:**
```bash
POST /autori
{
    "nome": "Umberto",
    "cognome": "Eco"
}
```

**Aggiungi un libro:**
```bash
POST /libri?autoreId=1
{
    "titolo": "Il Nome della Rosa",
    "anno": 1980
}
```
