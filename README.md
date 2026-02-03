# Checkout Retail

Aplicación de ejemplo desarrollada con **Spring Boot (v4.0.2)** que simula el proceso de checkout de una tienda retail.  
Permite enviar un carrito de productos al backend para calcular **subtotal, descuentos aplicados y total final**, mostrando el resultado en un **frontend simple en HTML/CSS/JS**.

---

## Tecnologías utilizadas

- **Java 17 (Amazon Corretto 17 recomendado)**
- **Spring Boot 4.0.2**
- **Gradle** como herramienta de construcción
- **Tomcat embebido**
- **Frontend estático (HTML, CSS y JavaScript)** servido desde Spring Boot

---

##  Estructura del proyecto
checkout-retail/
├── build.gradle
├── settings.gradle
├── src/
│ ├── main/
│ │ ├── java/com/example/checkoutretail/
│ │ │ ├── CheckoutRetailApplication.java
│ │ │ ├── controller/CheckoutController.java
│ │ │ ├── model/ (clases Cart, Item, Discount, etc.)
│ │ │ └── service/CheckoutService.java
│ │ ├── resources/
│ │ │ ├── application.properties
│ │ │ └── static/
│ │ │ └── checkout.html ← Frontend funcional
│ └── test/ (tests opcionales)
└── README.md

--

---

## Requisitos previos

Necesario instalar:

| Requisito | Versión recomendada | Verificar instalación |
|------------|--------------------|------------------------|
| **Java JDK** | 17 o superior (Amazon Corretto recomendado) | `java -version` |
| **Gradle** | 8.x o superior *(opcional, ya se incluye wrapper)* | `gradle -v` |
| **Git** | Cualquier versión reciente | `git --version` |

---

## ▶️ Instrucciones para ejecutar el proyecto localmente

### 1. Clonar el repositorio

```bash
git clone https://github.com/YaniraBalcazar/checkout-retail.git
cd checkout-retail


## Requisitos previos
./gradlew build

## Ejecutar la aplicación

./gradlew bootRun

## Por defecto, el servidor se levanta en:

http://localhost:8080

--

## Acceder al frontend del checkout

http://localhost:8080/checkout.html

**Verás el checkout visual, con los productos fijos definidos en el código y un selector de medio de pago.
Al presionar “Realizar checkout”, la página enviará el carrito al endpoint del backend (POST /api/checkout) y mostrará el subtotal, descuentos aplicados y total final.**


** Autor **

Yanira Balcazar
Full Stack Developer 