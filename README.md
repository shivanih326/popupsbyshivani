# Popups by Shivani — Workshops Management System

A starter full-stack project based on the supplied abstract. It uses Java 17, Spring Boot, Spring Data JPA, MySQL, HTML, CSS and vanilla JavaScript.

## Features
- Dashboard summary
- Create, list, edit and delete workshops
- Create, list, edit and delete participants
- Create and list bookings, update booking status via REST API
- Record payments and update payment status via REST API
- Simple reports summary

## Local requirements
1. JDK 17 or newer
2. Maven 3.9+ (or use IntelliJ IDEA's Maven support)
3. MySQL Server 8+

## Local setup
1. Install and start MySQL.
2. Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` for your local MySQL database. Do not put database credentials in this repository.
3. Open a terminal in the project folder and run:
   ```bash
   mvn spring-boot:run
   ```
4. Open `http://localhost:8080` in your browser.

The database named `popupsbyshivani` is created automatically if the MySQL user has permission. JPA creates/updates tables on startup.

## Free deployment: Render + TiDB Cloud Starter

The same Spring Boot service serves the frontend and API at one URL. TiDB is MySQL-compatible, but it is not the MySQL product named in the project abstract; confirm that substitution is acceptable if this is being graded against an exact technology list.

1. Create a free **TiDB Cloud Starter** instance and a database named `popupsbyshivani`. In the TiDB console, use **Connect** to get the host, port, username, password, and TLS settings. A Starter connection must use TLS. Use a JDBC URL in the form `jdbc:mysql://HOST:4000/popupsbyshivani?sslMode=VERIFY_IDENTITY`, replacing `HOST` and the port with the values TiDB gives you. Use its complete username, including the instance prefix.
2. Push this repository to GitHub. If this folder has no commit yet, create one before pushing. Never commit `.env` or credentials.
3. In Render, choose **New > Blueprint**, connect the GitHub repository, and use `render.yaml`. Select the Free web service. Fill in the prompted database values:

   | Variable | Value |
   | --- | --- |
   | `DB_URL` | TiDB JDBC URL with TLS |
   | `DB_USERNAME` | Complete TiDB username |
   | `DB_PASSWORD` | TiDB password |

   The Blueprint supplies `DB_DIALECT=org.hibernate.dialect.TiDBDialect`. The app reads Render's `PORT` automatically. If you create a Web Service manually instead, choose **Docker**, **Free**, and set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, and `DB_DIALECT`; use `/actuator/health` as the health check path.
4. After deployment, open the Render URL. Verify `/actuator/health` returns `{"status":"UP"}`, then create a sample workshop and refresh the page to verify it persists.

Render's free web service sleeps after 15 minutes without requests, so the first visit can take about a minute. TiDB Starter has a free usage quota; monitor it in the TiDB dashboard. Database connections require TLS.

## API endpoints
| Method | Endpoint | Purpose |
|---|---|---|
| GET, POST | `/api/workshops` | List/create workshops |
| GET, PUT, DELETE | `/api/workshops/{id}` | Read/update/delete workshop |
| GET, POST | `/api/participants` | List/create participants |
| PUT, DELETE | `/api/participants/{id}` | Update/delete participant |
| GET, POST | `/api/bookings` | List/create bookings |
| PATCH | `/api/bookings/{id}/status?value=CANCELLED` | Update booking status |
| DELETE | `/api/bookings/{id}` | Delete booking |
| GET, POST | `/api/payments` | List/create payment records |
| PATCH | `/api/payments/{id}/status?value=PAID` | Update payment status |
| GET | `/api/dashboard` | Dashboard counts |

### Example JSON
Create workshop:
```json
{
  "title": "Canvas Painting Workshop",
  "activity": "Canvas painting",
  "venue": "Aaromale Cafe",
  "eventDate": "2026-11-15",
  "fee": 499,
  "status": "UPCOMING"
}
```
Create participant:
```json
{"fullName":"Sample Participant","email":"sample@example.com","phone":"9876543210"}
```
Create booking (use IDs returned from your API):
```json
{"participant":{"id":1},"workshop":{"id":1},"status":"CONFIRMED"}
```
Create payment record:
```json
{"booking":{"id":1},"amount":499,"method":"UPI","status":"PENDING"}
```

## Important limitations
- This is a learning/demo starter, not a production-ready system.
- The management UI and API have no login. Anyone with the public URL can view participant records and create, edit, or delete data. Use only disposable sample data; do not store real participant or payment information in a public deployment.
- Payment records are manual; there is no online payment gateway or automated notification service.
- Avoid deleting participants/workshops that are referenced by bookings. The database may reject deletion to preserve relationships.
