/**
 * Programa que rellena los datos de la factura en fact.html
 * tomando los datos del GET request en el formulario de form.html
 */
class Factura {
    constructor(urlsp) {
        this.tipo = urlsp.get('tipo');
        this.fecha = urlsp.get('fecha');
        this.receptor = urlsp.get('receptor');
        this.cp = urlsp.get('cp');
        this.domicilio = urlsp.get('domicilio');
        this.localidad = urlsp.get('localidad');
        this.iva = urlsp.get('iva');
        this.cuit = urlsp.get('cuit');
        this.condiciones = urlsp.get('condiciones');
        this.remito = urlsp.get('remito');
        this.productos = [];
        this.subtotal = 0;
        for (let index=1; urlsp.get(`c${index}`) !== null; index++) { //mientras hay filas
            const row = [urlsp.get(`c${index}`), urlsp.get(`d${index}`), urlsp.get(`p${index}`), urlsp.get(`c${index}`)*urlsp.get(`p${index}`)];
            this.productos.push(row);

            this.subtotal += urlsp.get(`c${index}`)*urlsp.get(`p${index}`);
        }
        this.iva = this.subtotal * 0.21;
        this.totalNeto = this.subtotal + this.iva;
    }
}

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

//crear una lista en base al URL:
const urlsp = new URLSearchParams(window.location.search);

const factura = new Factura(urlsp);
fillData(factura);

function fillData(factura) {
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
    for (row of factura.productos) {
        for (cell of row) {
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