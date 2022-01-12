# Proyecto creado con "npm create-react-app"

## Scripts:

En la carpeta raíz, se puede ejecutar:
### `npm start`

Ejecuta la aplicación en modo de desarrollo. Asegurarse de ejecutar `npm install` antes, para instalar las dependencias.

### `npm test`

Ejecuta el entorno de pruebas

### `npm run build`

Transpila, minifica y hace tree shaking a todo el proyecto para la versión de lanzamiento

### `npm run eject`

**No se utiliza**

## Notas:

### SCSS:

La mayoría de componentes utilizan SCSS. Para transformarlo a CSS, se recomienda la extensión 'live sass compiler en VSC'. Los archivos de CSS no deberían ser modificados directamente.

### Archivos especiales:

1. package.json: declaración de dependencias
2. webpack.config.js: configuración del administrador de paquetes webpack
3. tsconfig.json: configuración del comportamiento de typescript
4. declaration.d.ts: exporta tipos de datos personalizados para typescript.
5. global.d.ts: exporta declaraciones especiales que permiten importar archivos .jpg, .png, .svg..

### Directorios:
* **dist**: generado por el transpilador de typescript (no modificar)
* **node_modules**: módulos de react. Para generar automáticamente a partir de las dependecias especificadas: npm install
* **public**: carpeta principal accesible por el servidor
* **src**: todos los recursos que serán transpilados por Webpack.