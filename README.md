# 🧠 BackendTP2 - Gestión de Pedidos

Este es un proyecto backend desarrollado en Java con Spring Boot y Maven. El objetivo del sistema es gestionar pedidos, productos y usuarios de forma sencilla.

## 🛠 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Maven
- Spring Security
- Spring Data JPA
- WebClient / RestTemplate
- MySql

---

## 🚀 Cómo clonar y ejecutar el proyecto

### ✅ 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/BackendTP2.git
cd BackendTP2
```
------ 

## 🚀 Creacion de Base de datos:

### 1. Iniciá MySQL desde XAMPP
- Abrí XAMPP

- Iniciá el servicio MySQL

- Entrá a phpMyAdmin: http://localhost/phpmyadmin

### 2. Creá las base de datos:

Desde phpMyAdmin:
### Para el microservicio de Ordenes:

- En la columna izquierda, hacé clic en "Nueva".

- Nombre de la base: orders_final

- Codificación: utf8mb4_general_ci

- Crear

### Para el microservicio de Productos:

- En la columna izquierda, hacé clic en "Nueva".

- Nombre de la base: products_final

- Codificación: utf8mb4_general_ci

- Crear

## 🚀 Sincronizamos con Maven, en caso de que sea necesario.

## 🚀 Compilacion:

Para poder correr el proyecto, necesitaremos compilar dos microservicios a la vez:
- OrdersApplication --> Es la clase main de compilacion del servicio de Ordenes.
- ApiBackendPracticeApplication --> Es la clase main de compilacion del servicio de Productos.
  
![Screenshot 2025-06-18 at 11 28 44 PM](https://github.com/user-attachments/assets/41943240-bc4f-47e0-bbe1-c25a183599c6)

Cuando tengamos corriendo ambos servicios, podremos probar el sistema en los Swaggers: 

-  Productos : http://localhost:8080/swagger-ui/index.html#
-  Orders: http://localhost:8082/swagger-ui/index.html#/

El servicio de Productos estara corriendo en el puerto 8080

El servicio de Ordenes estara corriendo en el puerto 8082.
  
  

## 🚀 A tener en cuenta:

En el Swagger no pide authenticacion, ya que era una experiencia media engorrosa colocar credenciales todo el tiempo: 

<img src="https://github.com/user-attachments/assets/9888dd15-ec64-4021-825c-e68b2b8e43d8" alt="Swagger sin autenticación" width="600" height="200" />

En caso de probar en Postman, podran usar el siguiente usuario:

<img src="https://github.com/user-attachments/assets/2190c4de-f72b-4986-bb71-052ca7be8f57" alt="Usuario de prueba Postman" width="600" height="200" />


Cualquier duda, contactarse por mail:
nahuelpaez86@gmail.com

Nahuel Paez. 
