Otros pasos
 - Agregar el mysql-connector a lib en la carpeta de glassfish
 - Agregar el pool en la consola de glassfish (Domain Console Glassfish/Resource/JDB/ConnectionPool)
 - Agregar el nombe jndi en la consola de glassfish (Domain Console Glassfish/Resource/JDB/jdbc Resource)
 - En Netbeans Services / DataBase / New Connection -- Crear una nueva conexion con el Driver de mysql-connector con la URL:
	jdbc:mysql://localhost:3306/personas?useSSL=false&useTimezone=true&serverTimezone=UTC 
(Parametros iguales, puerto(3306) segun como tengas y nombre de database personas(Segun como tengas))