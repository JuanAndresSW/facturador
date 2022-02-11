import React from "react";
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
import SelectPartakers from "../../../FormElements/SelectPartakers";
import ProductTable from "../../../FormElements/ProductTable";
import Observations from "../../../FormElements/Observations";
import PurchaseExtra from "../../../FormElements/PurchaseExtra";
import Type from "../../../FormElements/Type";
import DocData from "../../../../script/DocData";
import "./DocumentForm.css";
type props = {
  flux: string;
  type: string;
};
//devuelve un formulario que recolecta los datos necesitados por el back-end para generar un documento
export default function DocumentForm({ flux, type }: props): JSX.Element {
  DocData.fetchFormData();
  return (
    <>
      <form id="form" method="post" target="_blank" className="panel">
        <h1 className="title">{getTitle(type, flux)}</h1>
        <SelectPartakers />
        <Type type={type} />
        <ProductTable type={type} />
        <Observations type={type} />
        <PurchaseExtra type={type} />
        <button>Generar</button>
      </form>
    </>
  );
}

//encontrar un título apropiado para el formulario
function getTitle(type: string, flux: string): string {
  let title: string;
  switch (type) {
    case "purchase-order":
      title = "Nueva orden de compra ";
      break;
    case "remit":
      title = "Nuevo remito ";
      break;
    case "invoice":
      title = "Nueva factura ";
      break;
    case "debit-note":
      title = "Nueva nota de débito ";
      break;
    case "credit-note":
      title = "Nueva nota de crédito ";
      break;
    case "receipt-x":
      title = "Nuevo recibo X ";
      break;
    case "receipt":
      title = "Nuevo recibo simple ";
      break;
    case "promissory-note":
      title = "Nuevo pagaré ";
      break;
    case "check":
      title = "Nuevo cheque ";
      break;
    case "other":
      title = "Nueva operación ";
      break;
    default:
      title = "Algo salió mal ";
  }
  switch (flux) {
    case "in":
      title += "de entrada";
      break;
    case "out":
      title += "de salida";
      break;
    default:
      return;
  }
  return title;
}
