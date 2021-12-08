/**
 * Programa que rellena los datos de la factura en generador.html
 * tomando los datos del GET request en el formulario de formulario-js.html
 * Modifica los datos para que puedan ser representados correctamente en la factura.
 */

//instanciar las areas donde agregar los datos:
const list = document.getElementById(document.querySelector('header'));

//crear una lista en base al URL:
const url = window.location.search; //selecciona la barra de búsqueda

const urlsp = new URLSearchParams(url); //crea un objeto para buscar los parámetros pasados por el formulario

console.log(urlsp.toString());

for (const [key, value] of urlsp) {
    console.log(`${key}: ${value}`);
}