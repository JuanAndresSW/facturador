# facturador++

## Descripción

`facturador++` es una aplicación web didáctica que permite administrar una empresa comercial con distintos puntos de venta, y crear documentos comerciales.
Está desarrollada usando React, Java Spring Boot y MySQL.


## Configuraciones

El directorio raíz tiene dos carpetas:

* facturador: el back-end
* facturador_web: el front-end


### Configurar el back-end

  * Instalar Java 17: [OpenJDK 17](https://jdk.java.net/archive).
  * Instalar MySQL 8: [MySQL 8](https://dev.mysql.com/downloads/mysql/8.0.html).
  * Instalar Maven 3.9.9: [Apache Maven](https://maven.apache.org/download.cgi?).
  * Limpiar el Build e instalar dependencias del back-end: `mvn clean -f pom.xml && mvn install -f pom.xml`.
  * Montar la base de datos en `./facturador/data/`. En `./facturador/src/main/resources/` establecer los parámetros de conexión:

```yml
  datasource:
    password: #YOUR_PASSWORD
    url: jdbc:mysql://localhost:3306/facturador_db?useSSL=true&useTimezone=true&serverTimezone=UTC
    username: #YOUR_USERNAME
```

### Configurar el front-end

* Instalar dependencias del front-end con npm: `npm install`.
* Muchos componentes utilizan SCSS. Para editar estos archivos, si está usando Visual Studio Code, se recomienda la extensión 'live sass compiler', para convertir rápidamente a CSS.
* .env: en el archivo `./facturador_web/.env` podrá modificar la dirección a la cual se dirigen todas las peticiones HTTP.