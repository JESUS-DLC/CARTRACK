# CARTRACK JESUSDLC

## DESCRIPCIÓN

API CARTRACK, una api basada en Spring Boot que proporciona operaciones CRUD para la gestión de marcas, carros, servicios, inicio de sesión y registro de usuarios

## CARACTERISTICAS PRINCIPALES

- uso de patron FACADE
- uso de patron DTO
- uso de Controladores, Servicios, Repositorios y Entidades
- uso de paginación
- uso de specification
- uso de query params
- implementación de Spring Security
- migración de base de datos con Flyway
- manejo de excepciones
- documentación con swagger
- uso de mapstruct

## REQUERIMIENTOS

para correr la aplicacion necesitas

- jdk 11
- gradle
- PostgreSQL

## Correr la API 

se tiene que configurar el archivo application.yml para proporcionar tus credenciales
para la base de datos asi como el puerto, la llave para spring security y la duracion del token

### Correr localmente

### Running port 

url http://localhost:TU_PUERTO

### Swagger

 default url http://localhost:TU_PUERTO/swagger-ui/index.html#/