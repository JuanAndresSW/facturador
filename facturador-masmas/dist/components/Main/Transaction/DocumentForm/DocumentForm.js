import React from "react";
import SelectPartakers from '../../../FormElements/SelectPartakers/SelectPartakers';
import ProductTable from '../../../FormElements/ProductTable/ProductTable';
import './DocumentForm.css';
//return a form element to gather data needed by the server to generate a new business document
export default function DocumentForm(_a) {
    var flux = _a.flux, type = _a.type;
    return (React.createElement("form", { id: "form", method: "post", target: "_blank" },
        React.createElement("h1", null, getTitle(type, flux)),
        React.createElement(SelectPartakers, null),
        React.createElement(ProductTable, { type: type }),
        React.createElement("button", null, "Generar")));
}
//get a proper title for the form
function getTitle(type, flux) {
    var title;
    switch (type) {
        case 'purchase-order':
            title = "Nueva orden de compra ";
            break;
        case 'remit':
            title = "Nuevo remito ";
            break;
        case 'invoice':
            title = "Nueva factura ";
            break;
        case 'debit-note':
            title = "Nueva nota de débito ";
            break;
        case 'credit-note':
            title = "Nueva nota de crédito ";
            break;
        case 'receipt-x':
            title = "Nuevo recibo X ";
            break;
        case 'receipt':
            title = "Nuevo recibo simple ";
            break;
        case 'promissory-note':
            title = "Nuevo pagaré ";
            break;
        case 'check':
            title = "Nuevo cheque ";
            break;
        case 'other':
            title = "Nueva operación ";
            break;
        default: title = 'Algo salió mal ';
    }
    switch (flux) {
        case "in":
            title += "de entrada";
            break;
        case "out":
            title += "de salida";
            break;
        default: return;
    }
    return title;
}
/**
 * <div>
        <label htmlFor="tipo">Tipo</label>
        <select name="tipo" className="inputControl" id="tipo" required>
            <option selected value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
        </select>
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
                    <label htmlFor="remito">Remito <span>(opcional)</span></label>
                    <input name="remito" type="number" id="remito" className="inputControl" placeholder="xxxx-xxxxxxxxx" min="0001000000001" max="9999999999999" />
                </div>

                
                <button id="submit" type="submit" formMethod="get">Generar</button>
            </div>

 */ 
