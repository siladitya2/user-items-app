## User-items-app

A simple full-stack application demonstrating a filterable list of items. The backend is built with Java Spring Boot, and the frontend is a React Javascript app.

## Features

* **Filterable List**: Display a list of items that can be filter in real time.
* **Server-side Filtering**: Backend endpoint accepts a `filter` query parameter and returns matching items.
* **Testing** Unit test cases for frontend, unit, and integration test cases for backend.
* **Modern Tech Stack**: Java, Spring Boot, React JS, Docker.

* Note: I have hard-coded some items data in the backend using DataLoader.java. I have also provided an API (details below) to add item data on the backend.
## Tech Stack

* **Backend**: Java, Spring Boot, REST API
* **Frontend**: React, Javascript
* **Build Tools**: Gradle (Backend), npm/Yarn (Frontend)
* **Containeraization** Docker

## Prerequisites

* **Java 11+**
* **Gradle** (optional, wrapper included)
* **Node.js 16+** and **npm** or **Yarn**
* **Git**
* **Docker**

## Getting Started

### 1. Clone the repository

```bash
git clone git@github.com:siladitya2/user-items-app.git
cd user-items-app
```

### 2. Backend – Spring Boot

1. Navigate to the backend directory:

   ```bash
   cd user-items-service
   ```
2. Build and run the Spring Boot application:

   ```bash
   ./gradlew bootRun
   ```
3. The API will start on `http://localhost:8080`.

#### Backend Endpoints

* `GET /api/v1/items`

    * Returns all items.
* `GET /api/v1/items?filter={term}`

    * Returns items whose `name` contains `{term}` (case-insensitive).

**Example:**

```bash
curl "http://localhost:8080/api/v1/items?filter=A"
```

*  `POST /api/v1/item`

* Adds a new item. Accepts a JSON body with a name property.

**Example:**
  
```bash
  curl -X POST http://localhost:8080/api/v1/item \
     -H "Content-Type: application/json" \
     -d '{"name":"Grape"}'
```
Returns the created item.

### 3. Frontend – React TypeScript

1. Navigate to the frontend directory:

   ```bash
   cd user-items-ui
   ```
2. Install dependencies:

   ```bash
   npm install
   # or
   yarn install
   ```
3. Start the development server:

   ```bash
   npm start
   # or
   yarn start
   ```
4. Open your browser at `http://localhost:3000`.

## Usage

1. Start backend and frontend as described above.
2. In the browser, type a search term into the input field to filter the list of items.
3. Clear the input to return to the full list.


## Docker Compose

I provided a docker-compose.yml to build and run both backend and frontend together.

### Usage

From the project root, run:

   ```bash
docker-compose up --build
   ```

This will:

Build and start the backend service on http://localhost:8080

Build and start the frontend service on http://localhost:3000 (proxied to backend via container network)

Press CTRL+C to stop and tear down the containers.

## Future Enhancements
1. Support for Pagination.
2. Authentication and Authorization
3. Remove hard-coded server configurations(ex: port=8080, port=3000) and configure through properties file or environment variable
