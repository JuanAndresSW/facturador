<h1 align="center">
  <img src="./src/main/resources/static/logo192.png" alt="facturador++ logo" />
</h1>
<h2 align="center">
  ☕ facturador++ Java Spring Boot
</h2>

## ℹ️ Introduction
En este repositorio contiene la aplicaicon web facturador++, tambien esta las instrucciones para iniciar la base de datos


## 🏁 Como iniciar
  * `1.` Instalar Java 17: `Debera instalar OpenJDK-17 SITIO OFICIAL:` [OpenJDK 17](https://jdk.java.net/archive)
    * 1. Asegurarse de que en la instalacion les haya puesto el JAVA_HOME
  * `2.` Instalar MySQL 8: `Debera instalar MySQL SITIO OFICIAL:` [MySQL 8](https://dev.mysql.com/downloads/mysql/8.0.html).
    * 1. Con MySQL es suficiente (tendra que ejecutar desde consola), puede optar por tambien instalar Workbench (La interfaz grafica de MySQL)
  * `3.` Instalar Maven sera necesario en caso de no contar con un `IDE` que lo tenga `integrado` o previa instalacion
    * 1. En caso de ser necesario Sitio Oficial: [Apache Maven](https://maven.apache.org/download.cgi?.).
    * 2. Para ejecutar desde consola debera  definir`MAVEN_HOME='../ruta/apache-maven-version'`
    * 3. **RECOMENDACION PERSONAL** El IDE quedeberia de utilizar es IntelliJ IDEA, version Ultimate o Comunity [IntellIj IDEA](https://www.jetbrains.com/idea/download/?fromIDE=#section=windows)
  * `4.` Clonar el repositorio: `git clone https://github.com/conjunto-solucion/facturador.git`.
  * `5.` Inicia a desarrollar!!

## Como iniciar la base de datos
  * `1.` Montar la Base de datos en la carpeta `./facturador/data se encuentra el .sql ejecutar el script en el MySQL`.
  * `2.` Revisar el archivo application.yml se encuentran `./facturador/src/main/resources/`
    * 1. La parte que nos importe aqui es esta:
```yml
#Buscamos la linea que diga esto y cambiar con sus datos
  datasource:
    password: #YOUR_PASSWORD
    #DNS CONEXION A MYSQL
    url: jdbc:mysql://localhost:3306/facturador_db?useSSL=true&useTimezone=true&serverTimezone=UTC
    username: #YOUR_USERNAME
#Aqui habra mas configuraciones modificarlas a gusto
```

## Como actualizar dependencias
  * `1.` Limpiar el Build e instalar dependencias: `mvn clean -f pom.xml && mvn install -f pom.xml`.
    * 1. Puede hacerlo desde la interfaz grafica de IntelliJ **RECOMENDABLE**
    * 2. Desde la interfaz grafica click derecho al archivo pom.xml opcion Maven > Reload project
    * 3. En consola debe estar en la ruta del pom.xml
