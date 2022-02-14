import React, { useEffect, useState } from "react";
import fetchFormData from "services/operation/fetchFormData";

//Componentes de documento generado.
import {
  Invoice,
  CreditNote,
  DebitNote,
  Receipt,
  PurchaseOrder,
  Remittance,
  Check,
  PromissoryNote,
} from "../Documents";

//Componentes input.
import SelectPartakers from "./SelectPartakers";
import ProductTable from "./ProductTable";
import Observations from "./Observations";
import PurchaseExtra from "./PurchaseExtra";
import Type from "./Type";

//Tipos.
import { document } from 'utils/types';
type props = {
  flux: string;
  type: string; //nombre del documento comercial
};

/**Devuelve un formulario que recolecta los datos necesitados por el back-end para generar un documento.
 * @param flux Flujo de emisión del documento. Valores aceptados: 'in' | 'out'.
 * @param type Tipo de documento comercial en inglés y kebab-case.
*/
export default function OperationForm({ flux, type }: props): JSX.Element {

  //Solicitar `dataToShow` en el primer renderizado.
  useEffect(() => {
    fetchFormData(handleResponse);
  }, []);

  //Maneja la respuesta de los requests al servidor
  function handleResponse(state: number, data: string): void {
    switch (state) {
      case 0: break;
      case 200:
        setDataToShow({
          ...dataToShow,
          partakers: JSON.parse(data),
          currentPoint: JSON.parse(data).pointsOfSale[0],
          currentPartner: JSON.parse(data).partners[0]
        });
        setDataToSend({
          ...dataToSend,
          pointOfSale: JSON.parse(data).pointsOfSale[0],
          partner: JSON.parse(data).partners[0]
        });break;
    }
  }

  //Los datos del servidor necesarios para mostrar el formulario.
  const [dataToShow, setDataToShow] = useState({
    partakers: null,
    currentPoint: null,
    currentPartner: null,
    currentGroup: null
  })

  //Los datos a ser enviados al servidor.
  const [dataToSend, setDataToSend]:
    [document, React.Dispatch<React.SetStateAction<document>>] = useState({
      documentType: type,
      pointOfSale: undefined,
      partner: undefined,
      group: undefined
    });

  /**Las funciones utilizadas para cambiar `dataToSend`.*/
  const setter = {
    setPointOfSale: (point: { id: number, address: string, name: string }) => {
      setDataToSend({ ...dataToSend, pointOfSale: point.id });
      setDataToShow({ ...dataToShow, currentPoint: point });
    },
    setPartner: (partner: { id: number, address: string, name: string }) => {
      setDataToSend({ ...dataToSend, partner: partner.id });
      setDataToShow({ ...dataToShow, currentPartner: partner });
    }
  }

  return (
    <>
      <form id="form" method="post" target="_blank" className="panel">
        <h1 className="title">{getTitle(type, flux)}</h1>
        <SelectPartakers setter={setter} dataToShow={dataToShow} flux={flux} />
        <Type type={type} />
        <ProductTable type={type} />
        <Observations type={type} />
        <PurchaseExtra type={type} />
        <button>Generar</button>
      </form>
    </>
  );
}

/**Encuentra un título apropiado para el formulario.*/
function getTitle(type: string, flux: string): string {
  let title: string;
  switch (type) {
    case "purchase-order": title = "Nueva orden de compra "; break;
    case "remittance": title = "Nuevo remito "; break;
    case "invoice": title = "Nueva factura "; break;
    case "debit-note": title = "Nueva nota de débito "; break;
    case "credit-note": title = "Nueva nota de crédito "; break;
    case "receipt-x": title = "Nuevo recibo X "; break;
    case "receipt": title = "Nuevo recibo simple "; break;
    case "promissory-note": title = "Nuevo pagaré "; break;
    case "check": title = "Nuevo cheque "; break;
    case "other": title = "Nueva operación "; break;
    default: return "Algo salió mal";
  }
  switch (flux) {
    case "in": title += "de entrada"; break;
    case "out": title += "de salida"; break;
    default: return;
  }
  return title;
}
