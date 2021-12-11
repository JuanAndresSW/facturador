export class Factura {
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