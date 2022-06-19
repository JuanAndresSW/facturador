# Proyecto creado con "npm create-react-app"
Aplicación web didáctica de contabilidad basada en React.

## Scripts:

En la carpeta raíz, se puede ejecutar:
### `npm start`

Ejecuta la aplicación en modo de desarrollo. Asegurarse de ejecutar `npm install` antes, para instalar las dependencias.

### `npm test`

Ejecuta el entorno de pruebas

### `npm run build`

Transpila, minifica y hace tree shaking a todo el proyecto para la versión de lanzamiento

## Notas:

### SCSS:

Muchos componentes utilizan SCSS. Para transformarlo a CSS, se recomienda la extensión 'live sass compiler en VSC'.

### Archivos especiales:

1. package.json: declaración de dependencias
3. tsconfig.json: configuración del comportamiento de typescript
5. global.d.ts: exporta declaraciones especiales que permiten importar archivos .jpg, .png, .svg..

### Directorios:
* **node_modules**: módulos de react. Para generar automáticamente a partir de las dependecias especificadas: npm install
* **public**: carpeta principal accesible por el servidor
* **src**: todos los recursos que serán empaquetados por Webpack.