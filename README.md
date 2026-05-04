# Bank System - Microservicios con Spring Boot
Sistema bancario distribuido desarrollado con arquitectura de microservicios, implementado como prueba técnica para **Banco Pichincha** por **NTT DATA**.
## Arquitectura
El sistema está compuesto por 4 servicios principales que se comunican a través de **Eureka Server** para el service discovery y **Apache Kafka** como broker de mensajería para comunicación asíncrona entre microservicios.
                                ┌─────────────────┐
                                │   Eureka Server  │
                                │     :8761        │
                                └────────┬─────────┘
                                         │ register/discover
                                         ▼
┌─────────────────────────────────────────────────────────────────────┐
│                        API Gateway :8009                            │
│              (Single entry point + Load Balancing)                  │
└──┬──────────────────────────────┬───────────────────────────────────┘
   │/v1/api/customers/**          │/v1/api/accounts/**
   │                              │/v1/api/movements/**
   ▼                              ▼
┌──────────────────────┐   ┌──────────────────────────────────┐
│  Customer Service    │   │  Account & Movement Service      │
│  :8080               │   │  :8081                           │
│                      │   │                                  │
│ - CRUD Clientes      │   │ - CRUD Cuentas                   │
│ - Reportes JSON/Excel│   │ - CRUD Movimientos               │
│ - Kafka CONSUMER     │◄──│ - Kafka PRODUCER                 │
└──────────┬───────────┘   └──────────────┬───────────────────┘
           │                              │
           │         ┌────────────┐       │
           └────────►│  Kafka     │◄──────┘
                     │  :9092     │
                     │cliente-topic│
                     └────────────┘
                           │
                           ▼
                    ┌──────────────┐
                    │   MySQL      │
                    │  (R2DBC)     │
                    └──────────────┘
## Componentes
| Servicio | Puerto | Descripción |
|---|---|---|
| **Eureka Server** | 8761 | Service Discovery - Registro centralizado de microservicios |
| **API Gateway** | 8009 | Gateway reactivo (WebFlux) - Enrutamiento y load balancing |
| **Customer Service** | 8080 | Gestión de clientes/personas y generación de reportes |
| **Account/Movement Service** | 8081 | Gestión de cuentas bancarias y movimientos (depósitos/retiros) |
| **Apache Kafka** | 9092 | Broker de mensajería - Comunicación asíncrona entre servicios |
| **MySQL** | 3306 | Base de datos relacional - Persistencia vía R2DBC (reactivo) |
## Tech Stack
| Componente | Tecnología |
|---|---|
| **Lenguaje** | Java 21 |
| **Framework** | Spring Boot 3.5.x / 4.0.6 |
| **Cloud** | Spring Cloud 2025.x |
| **Build Tool** | Maven |
| **Database** | MySQL con R2DBC (reactivo) |
| **Message Broker** | Apache Kafka (KRaft mode - sin Zookeeper) |
| **Service Discovery** | Netflix Eureka |
| **API Gateway** | Spring Cloud Gateway (WebFlux) |
| **API Docs** | SpringDoc OpenAPI 3.0.2 (Swagger UI) |
| **Tracing** | Zipkin Brave |
| **Containerization** | Docker + Docker Compose |
| **Testing** | JUnit 5, Karate BDD |
| **Excel Export** | Apache POI 5.4.0 |
## Patrones de Arquitectura
- **Arquitectura Hexagonal (Clean Architecture)**: Puertos de dominio (in/out), casos de uso, adaptadores de infraestructura
- **Patrón Repositorio**: Repositorios reactivos R2DBC con adapters
- **Strategy Pattern**: Generación de reportes (JSON vs Excel vía `ReportGeneratorFactory`)
- **Programación Reactiva**: WebFlux con `Mono<T>` y `Flux<T>`
- **Service Discovery**: Registro Eureka con routing load-balanced
- **Event-Driven**: Kafka para comunicación asíncrona entre servicios
## APIs
### Customer Service (`/v1/api/customers`)
| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/v1/api/customers` | Listar todos los clientes (paginado) |
| `GET` | `/v1/api/customers/{id}` | Obtener cliente por ID |
| `POST` | `/v1/api/customers` | Crear nuevo cliente |
| `PUT` | `/v1/api/customers/{id}` | Actualizar cliente |
| `DELETE` | `/v1/api/customers/{id}` | Eliminar cliente |
| `GET` | `/v1/api/customers/reports/{clienteId}` | Generar reporte de movimientos (JSON/Excel) |
### Account Service (`/v1/api/accounts`)
| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/v1/api/accounts` | Listar todas las cuentas (paginado) |
| `GET` | `/v1/api/accounts/{id}` | Obtener cuenta por ID |
| `POST` | `/v1/api/accounts` | Crear nueva cuenta |
| `PUT` | `/v1/api/accounts/{id}` | Actualizar cuenta |
| `DELETE` | `/v1/api/accounts/{id}` | Eliminar cuenta |
### Movement Service (`/v1/api/movements`)
| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/v1/api/movements/account/{numeroCuenta}` | Obtener movimientos de una cuenta |
| `GET` | `/v1/api/movements/account/{numeroCuenta}/last` | Obtener último movimiento |
| `GET` | `/v1/api/movements/{id}` | Obtener movimiento por ID |
| `POST` | `/v1/api/movements` | Crear nuevo movimiento (publica evento a Kafka) |
## Flujo de Eventos Kafka
1. El **Movement Service** crea un movimiento y publica un `MovementEventDTO` en el topic `cliente-topic`
2. El **Customer Service** consume el evento y persiste los datos en la tabla `report_customer_movements`
3. Los reportes se generan a partir de esta tabla en formato **JSON** o **Excel**
## Requisitos Previos
- **Java 21** (para desarrollo local)
- **Maven 3.9+**
- **Docker** y **Docker Compose**
- **MySQL 8.0+** (instalado localmente)
## Pasos para Levantar el Proyecto
### 1. Configurar Base de Datos MySQL
Crear la base de datos local y ejecutar el script de inicialización:
```bash
# Conectar a MySQL y crear la base de datos
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS pichincha_interv;"
# Ejecutar el script de esquema y datos semilla
mysql -u root -p pichincha_interv < BaseDatos.sql
2. Build de las Imágenes Docker
Construir las imágenes de cada microservicio desde la raíz del proyecto:
# Build Eureka Server
docker build -t tech-test-eureka-server-slim ./eureka-server
# Build API Gateway
docker build -t tech-test-api-gateway-slim ./api-gateway
# Build Customer Service
docker build -t tech-test-spring-customer ./tech-test-spring
# Build Account/Movement Service
docker build -t tech-test-spring-account-movement ./tech-test-spring-2
3. Levantar Servicios con Docker Compose
Levantar toda la infraestructura (Kafka, Eureka, Gateway, microservicios) pasando las credenciales de MySQL:
SPRING_DATA_MYSQL_URL="jdbc:mysql://host.docker.internal:3306/pichincha_interv" \
SPRING_DATA_MYSQL_USERNAME="root" \
SPRING_DATA_MYSQL_PASSWORD="tu_password_aqui" \
docker compose up -d
> Nota: host.docker.internal permite que los contenedores accedan a MySQL corriendo en tu máquina local. En Linux puede ser necesario agregar --network host o usar la IP de tu máquina.
4. Verificar que Todo Está Corriendo
# Verificar contenedores
docker compose ps
# Verificar servicios registrados en Eureka
curl http://localhost:8761/eureka/apps
# Verificar health del gateway
curl http://localhost:8009/actuator/health
5. Detener Servicios
docker compose down
Estructura del Proyecto
interview_test_spring_java/
├── eureka-server/              # Service Discovery (puerto 8761)
├── api-gateway/                # API Gateway reactivo (puerto 8009)
├── tech-test-spring/           # Customer Service (puerto 8080)
├── tech-test-spring-2/         # Account & Movement Service (puerto 8081)
├── docker-compose.yaml         # Orquestación de infraestructura
├── BaseDatos.sql               # Schema MySQL + datos semilla
└── Bank System - Prueba Tecnica.postman_collection.json  # Colección Postman
```
## Testing con Postman
Se incluye una colección de Postman (Bank System - Prueba Tecnica.postman_collection.json) con todos los endpoints configurados para probar el sistema.
Swagger UI
Cada microservicio expone su documentación OpenAPI en:
- Customer Service: http://localhost:8080/swagger-ui.html
- Account/Movement Service: http://localhost:8081/swagger-ui.html
## Base de Datos
Tablas
Tabla	Descripción
Cliente	Datos de clientes (nombre, género, DNI, contraseña, estado, teléfono, dirección)
Accounts	Cuentas bancarias (número, tipo AHORRO/CORRIENTE, saldo inicial, estado)
Movements	Movimientos/transacciones (valor, tipo CREDITO/DEBITO, saldo)
report_customer_movements	Tabla de reportes (alimentada por eventos Kafka)
## Reglas de Negocio
- Los movimientos de tipo DEBITO verifican saldo suficiente antes de procesarse
- El valor del movimiento debe ser mayor a cero
- Las cuentas pueden ser de tipo AHORRO o CORRIENTE