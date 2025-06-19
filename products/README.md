# 📦 API de Productos

Esta API RESTful permite crear, consultar, actualizar y eliminar productos usando Spring Boot.

Base path: `/api/products`

---

## 🛠️ Configuración del entorno

Esta aplicación está **preconfigurada** para conectarse a una base de datos **MySQL local** usando **XAMPP**.

### ⚙️ `application.properties`
```properties
spring.application.name=Api-Backend-Practice

# Conexión a MySQL (XAMPP)
spring.datasource.url=jdbc:mysql://localhost:3306/backend_practice?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
```

### 🧩 Requisitos para usar esta configuración
Tener XAMPP instalado y el servicio MySQL activo.

Crear una base de datos llamada backend_practice en phpMyAdmin.

Usar el usuario por defecto:

- Usuario: root

- Contraseña: (vacía)

## 🔄 Endpoints

### ➕ Crear producto

**POST** `/api/products`

- **Descripción**: Crea un nuevo producto.
- **Request Body**:
```json
{
  "name": "Producto A",
  "price": 100.0,
  "description": "Descripción del producto"
}

```

### ➕ Obtener todos los productos

**GET** `/api/products`

- **Descripción**: Devuelve una lista de todos los productos.
- **Respuesta**:

```json
[
  {
    "id": 1,
    "name": "Producto A",
    "price": 10.99,
    "description": "Descripción del producto A"
  },
  ...
]

```

### ➕ Obtener producto por ID

**GET** `/api/products/{id}`

- **Descripción**: Devuelve los datos de un producto específico.
- **Respuesta**:

```json
{
  "id": 1,
  "name": "Producto A",
  "price": 10.99,
  "description": "Descripción del producto A"
}

```


### ➕ Editar producto por ID

**PUT** `/api/products/{id}`

- **Descripción**: Edita un producto existente.
- **Body**:

```json
{
  "name": "Producto actualizado",
  "price": 12.50,
  "description": "Nueva descripción"
}
```

### ➕ Editar producto por ID

**PUT** `/api/products/{id}`

- **Descripción**: Edita un producto existente.
- **Body**:

```json
{
  "name": "Producto actualizado",
  "price": 12.50,
  "description": "Nueva descripción"
}
```
- **Respuesta**:

```json
{
  "name": "Producto actualizado",
  "price": 12.50,
  "description": "Nueva descripción"
}
```

### ➕ Eliminar producto 

**DELETE** `/api/products/{id}`

- **Descripción**: Elimina un producto existente por su ID.
- Parámetros de ruta:
    id (Long): ID del producto
- **Respuesta**: Codigo 200

