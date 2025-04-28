📚 Proyecto Backend - API de Registros
Este proyecto implementa una API REST utilizando Spring Boot, conectada a una base de datos MySQL, para la gestión de usuarios (registro, login y listado).

🚀 Tecnologías utilizadas
Java 21

Spring Boot 3

Spring Web

Spring Data JPA

Spring Security

MySQL

Jakarta Validation

Lombok

JUnit 5 + Mockito

📦 Funcionalidades principales
## Registro de usuarios con validación de datos.

## Inicio de sesión con validación de credenciales.

## Listado de usuarios registrados.

## Contraseñas encriptadas usando BCrypt.

## Manejo centralizado de errores.

## API lista para integrarse con un frontend en Angular.

⚙️ Configuración inicial
Crear la base de datos en MySQL:

```CREATE DATABASE form_dev;```
Configurar application.properties con tus credenciales de MySQL.

Levantar el proyecto:

```./mvnw spring-boot:run```
API disponible en:


```http://localhost:8080/api```
🧪 Pruebas
El proyecto incluye pruebas unitarias para validar la lógica de negocio en UserService.

Ejecutar:


```./mvnw test```
📄 Endpoints principales
POST /api/register → Registro de usuarios.

POST /api/login → Inicio de sesión.

GET /api/users → Listar usuarios registrados.

GET / → Ruta de prueba para confirmar que el backend está activo.