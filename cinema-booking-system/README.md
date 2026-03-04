# Cinema Booking System

Full-stack cinema booking application built with **Spring Boot** and **Angular**.

## Tech Stack

Backend
- Spring Boot
- Spring Security (JWT)
- REST API

Frontend
- Angular Standalone Components
- HttpClient
- Angular Routing

## Features

- User authentication (JWT)
- Role-based authorization
- Admin can add and delete movies
- Users can book seats
- Seat availability tracking
- Exception handling with UI error display

## Running the Project

### Backend

```bash
mvn spring-boot:run
```

Runs on:

```
http://localhost:8080
```

### Frontend

```bash
cd frontend
npm install
ng serve
```

Runs on:

```
http://localhost:4200
```

## Test Accounts

Admin

```
username: admin
password: admin123
```

User

```
username: user
password: user123
```

## API Examples

GET movies

```
GET /movies
```

Book seat

```
POST /bookings
```