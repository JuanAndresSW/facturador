import React from "react";
import {Factura, NotaDeCredito, NotaDeDebito, Recibo, OrdenDeCompra, Remito, Cheque, Pagare, Otro}
from '../Documents';
import './DocumentForm.css';
type props = {
    flux: string,
    type: string
}
export default function DocumentForm({ flux, type }: props): JSX.Element {

    return (
        <form id="form" method="get" target="_blank" className="document-form">
            <h1>{getTitle(type, flux)}</h1>
            <div>
                <div>
                    <label htmlFor="tipo">Tipo</label>
                    <select name="tipo" className="inputControl" id="tipo" required>
                        <option selected value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                    </select>
                </div>
            </div>

            <div>
                <div>
                    <label htmlFor="receptor">Receptor</label>
                    <input name="receptor" type="text" id="receptor" className="inputControl" placeholder="Cliente" maxLength={30} required />
                </div>

                <div>
                    <label htmlFor="domicilio">Domicilio</label>
                    <input name="domicilio" type="text" id="domicilio" className="inputControl" value="Av. Rivadavia" maxLength={30} required />
                </div>

                <div>
                    <label htmlFor="localidad">Localidad</label>
                    <input name="localidad" type="text" id="localidad" className="inputControl" value="Puerto Iguazú" maxLength={20} required />
                </div>

                <div>
                    <label htmlFor="cp">Código Postal</label>
                    <input name="cp" type="number" id="cp" className="inputControl" placeholder="" min="1111" max="9999" required />
                </div>

                <div>
                    <label>I.V.A.</label>
                    <label><input value="Responsable Inscripto" type="radio" name="iva" id="iva-ri" checked />Responsable inscripto</label>
                    <label><input value="Responsable Monotributista" type="radio" name="iva" id="iva-mt" />Responsable Monotributista</label>
                    <label><input value="Sujeto Exento" type="radio" name="iva" id="iva-ex" />Exento</label>
                    <label><input value="Consumidor Final" type="radio" name="iva" id="iva-cf" />Consumidor Final</label>
                </div>

                <div>
                    <label htmlFor="cuit">C.U.I.T.</label>
                    <input name="cuit" type="number" id="cuit" className="inputControl" value="33342343216" min="20100000000" max="33999999999" required />
                </div>
            </div>


            <div>
                <div>
                    <label>Condiciones de venta</label>
                    <label><input value="Cuenta Corriente" type="radio" name="condiciones" id="cond-cc" checked />Cuenta Corriente</label>
                    <label><input value="Al contado" type="radio" name="condiciones" id="cond-cont" />Al contado</label>
                    <label><input value="Pagaré" type="radio" name="condiciones" id="cond-pag" />Pagaré</label>
                    <label><input value="err" type="radio" name="condiciones" id="cond-otro" />Otro:</label>
                    <label><input disabled type="text" id="txf-cond-otro" className="inputControl" maxLength={20} /></label>

                </div>

                <div>
                    <label htmlFor="remito">Remito <span>(opcional)</span></label>
                    <input name="remito" type="number" id="remito" className="inputControl" placeholder="xxxx-xxxxxxxxx" min="0001000000001" max="9999999999999" />
                </div>

                {/*Arículos de la venta*/}
                <div id="productos">
                    <label>Cantidad</label><label>Detalle</label><label>Precio</label>

                    <input name="c1" type="number" className="inputControl" max="999999" />
                    <input name="d1" type="text" className="inputControl" maxLength={20} />
                    <input name="p1" type="number" className="inputControl" max="999999" />
                    {/*los números son cambiados con js cuando se agregan más filas*/}
                </div>
                <input type="button" id="btn-add-row" value="+" />
                <button id="submit" type="submit" formMethod="get">Generar</button>
            </div>

        </form>
    )
}

function getTitle(type: string, flux:string):string {
    let title:string;
    switch (type) {
        case 'orden-de-compra': title="Nueva orden de compra ";break;
        case 'remito': title="Nuevo remito ";break;
        case 'factura': title="Nueva factura ";break;
        case 'nota-de-debito': title="Nueva nota de débito ";break;
        case 'nota-de-credito': title="Nueva nota de crédito ";break;
        case 'recibo-x': title="Nuevo recibo X ";break;
        case 'recibo': title="Nuevo recibo simple ";break;
        case 'pagare': title="Nuevo pagaré ";break;
        case 'cheque': title="Nuevo cheque ";break;
        case 'otro': title="Nueva operación ";break;
        default: title='Algo salió mal '
    }
    switch (flux) {
        case "in": title+="de entrada";break;
        case "out": title+="de salida";break;
        default:return;
    }
    return title;
}
/*GET generado (ejemplo):
?tipo=factura-A&fecha=2021-12-07&receptor=Cliente&domicilio=Av.+Rivadavia&localidad=Puerto+Iguaz%C3%BA&cod=3370&iva=ri&cuit=33453395715&cond=cc&rem=34348976547
*/