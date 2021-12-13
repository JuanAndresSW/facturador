/**
 * Programa que rellena los datos de la factura en fact.html
 * tomando los datos del GET request del formulario de form.html
 */
import {Factura} from "./Factura.js";

//crear una lista en base al URL:
const urlsp = new URLSearchParams(window.location.search);
const factura = new Factura(urlsp);
fillData(factura);

function fillData(factura) {
    //instanciar las areas donde agregar los datos:
    const tipo = document.getElementById('tipo');
    const fecha = document.getElementById('fecha');
    const receptor = document.getElementById('receptor');
    const cp = document.getElementById('cp');
    const domicilio = document.getElementById('domicilio');
    const localidad = document.getElementById('localidad');
    const iva = document.getElementById('iva');
    const cuit = document.getElementById('cuit');
    const condiciones = document.getElementById('condiciones');
    const remito = document.getElementById('remito');
    const productos = document.getElementById('product-table');
    const totales = document.getElementById('totales');
    
    tipo.textContent += factura.tipo;
    fecha.textContent += factura.fecha;
    receptor.textContent += factura.receptor;
    cp.textContent += factura.cp;
    domicilio.textContent += factura.domicilio;
    localidad.textContent += factura.localidad;
    iva.textContent += factura.iva;
    cuit.textContent += factura.cuit;
    condiciones.textContent += factura.condiciones;
    remito.textContent += factura.remito;
    //tabla de productos
    for (let row of factura.productos) {
        for (let cell of row) {
            const child = document.createElement('p');
            child.textContent = cell;
            productos.appendChild(child);
        }
    }   
    //tabla de totales
    const pSubtotal = document.createElement('p');
    const pIva = document.createElement('p');
    const pTotalNeto = document.createElement('p');
 
    pSubtotal.textContent = `Subtotal: ${factura.subtotal}`;
    pIva.textContent = `IVA: ${factura.iva}`;
    pTotalNeto.textContent = `Total neto: ${factura.totalNeto}`;

    totales.insertAdjacentElement('beforeend', pSubtotal);
    totales.insertAdjacentElement('beforeend', pIva);
    totales.insertAdjacentElement('beforeend', pTotalNeto);
}