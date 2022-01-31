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
  Other,
} from "../Documents";
import TransactionTypes from "../TransactionTypes";
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
//return a form element to gather data needed by the server to generate a new business document
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

//get a proper title for the form
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
