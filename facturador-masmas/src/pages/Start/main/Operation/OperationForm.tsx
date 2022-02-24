import React, { FC, ReactNode, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import fetchFormData from "services/operation/fetchFormData";
import Cond from 'utils/Cond';

//Componentes de documento generado.
import { Invoice, CreditNote, DebitNote, Receipt, PurchaseOrder, Remittance, Check, PromissoryNote, } from "./documents";

//Componentes del formulario.
import { DateInput, ErrorMessage, Field, Form, Radio, Select, Submit, Switch, Table, Textarea } from "components/formComponents";
import { Section } from 'components/layout'
import { BiChevronsDown, BiChevronsUp, BiGroup, BiPlusCircle, BiUser } from "react-icons/bi";

//Tipos.
import {document} from 'utils/types'
type props = {
  flux: "in" | "out";
  type: ("purchase-order" | "remittance" | "invoice" | "debit-note" | "credit-note" | "receipt-x" | "receipt" | "promissory-note" | "check" | "other");
};

/**
 * Devuelve un formulario que recolecta los datos necesitados por el back-end para generar un documento.
 * @param flux Flujo de emisión del documento. Valores aceptados: 'in' | 'out'.
 * @param type Tipo de documento comercial en inglés y kebab-case.
 */
export default function OperationForm({ flux, type }: props): JSX.Element {

  //Solicitar los datos a mostrar en el primer renderizado.
  useEffect(() => fetchFormData(handleResponse), []);
  function handleResponse(state: number, data: string): void {
    if (state === 200) {
      setDisplay({
        ...display,
        pointsOfSale: JSON.parse(data).pointsOfSale,
        partners: JSON.parse(data).partners,
        groups: JSON.parse(data).groups,
        root: JSON.parse(data).root,
      });
    } else setError(data);
  }

  //Los datos del servidor necesarios para mostrar el formulario.
  const [display, setDisplay] = useState({
    pointsOfSale: undefined,
    partners: undefined,
    groups: undefined,
    root: false,
  })

  //Mensaje de error.
  const [error, setError] = useState("hello");

  // #### Los datos a ser enviados al servidor. #### //
  //Todos.
  const [pointOfSale, setPointOfSale] =     useState("");
  const [useGroup, setUseGroup] =           useState(false);
  const [partner, setPartner] =             useState("");
  const [group, setGroup] =                 useState("");
  //Factura, notas, remito y orden de compra.
  const [productTable, setProductTable] =   useState([["1", "", "1"]]);
  const [vat, setVat] =                     useState("21%");
  const [observations, setObservations] =   useState("");
  //Recibo x.
  const [payer, setPayer] =                         useState("");
  const [paymentMethods, setPaymentMethods] =       useState([["", "", ""]]);
  const [paymentImputation, setPaymentImputation] = useState([["", "", "", ""]]);
  const [detailOfValues, setDetailOfValues] =       useState([["", "", "", "", ""]]);
  //Orden de compra.
  const [seller, setSeller] =               useState("");
  const [conditions, setConditions] =       useState("");
  const [preferredDate, setPreferredDate] = useState("");
  const [dispatchPlace, setDispatchPlace] = useState("");
  const [deliverer, setDeliverer] =         useState("");
  /*[document, React.Dispatch<React.SetStateAction<document>>]*/


  return (
    <Form title={getTitle(type, flux)}>

      <ErrorMessage message={error} />

      <Section label="Partícipes">

        <Div>
          <Select options={display.pointsOfSale} bind={[pointOfSale, setPointOfSale]}
            fallback={display.root ? "No tienes ningún punto de venta. Crea tu primero:" : ""} />
          <PlusIcon link={"/"} cond={display.root} />
        </Div>

        <Cond bool={flux==="in"}>
          <BiChevronsUp style={{ margin: "1.2rem auto", display: "block", cursor: "default", fontSize: "2rem", color: "white" }} />
        </Cond>

        <Cond bool={flux==="out"}>
          <BiChevronsDown style={{ margin: "1.2rem auto", display: "block", cursor: "default", fontSize: "2rem", color: "white" }} />
        </Cond>

        <Switch falseIcon={<BiUser />} trueIcon={<BiGroup />} bind={[useGroup, setUseGroup]} />

        <Div>
          <Select
            options={useGroup ? display.groups : display.partners}
            bind={useGroup ? [group, setGroup] : [partner, setPartner]}
            fallback={useGroup ? "No tienes ningún grupo. Crea tu primero:" : "No tienes ningún socio. Crea tu primero:"}
          />
          <PlusIcon link="/" cond={display.root} />
        </Div>

      </Section>

      <Section label="Datos de la operación">

        <Cond bool={("purchase-order" + "remittance" + "invoice" + "debit-note" + "credit-note").includes(type)}>
          <Table
            headers={[{ th: "Cantidad", type:"number" }, { th: "Descripción" }, { th: "Precio",type:"numebr" }]}
            bind={[productTable, setProductTable]} maxRows={10}
          />
        </Cond>

        <Cond bool={("invoice" + "debit-note" + "credit-note").includes(type)}>
          <Radio legend="IVA" options={["21%", "10%", "4%", "0%"]}
          bind={[vat, setVat]} />
        </Cond>

        <Cond bool={("receipt-x").includes(type)}>
          <Field label="Pagador" bind={[payer, setPayer]} />
          <Table label="Forma de pago"
            headers={[{ th: "Cheque",type:"number"}, { th: "Documentos", type:"number" }, { th: "Efectivo",type:"number"}]}
            bind={[paymentMethods, setPaymentMethods]} maxRows={1}
          />
          <Table label="Imputación del pago"
            headers={[{th:"Tipo"},{th:"Número",type:"number"},{th:"Importe",type:"number"},{th:"Abonado",type:"number"} ]}
            bind={[paymentImputation, setPaymentImputation]} maxRows={3}
          />
          <Table label="Detalle de valores"
            headers={[{th:"Tipo"},{th:"Banco"},{th:"Número"},{th:"Fecha de depósito", type:"date"},{th:"Importe",type:"number"}]}
            bind={[detailOfValues, setDetailOfValues]} maxRows={3}
          />
        </Cond>
        
      </Section>

      <Section label="Datos opcionales">
        <Cond bool={("purchase-order" + "remittance" + "invoice" + "debit-note" + "credit-note").includes(type)}>
          <Textarea label="Observaciones" bind={[observations, setObservations]} />
        </Cond>

        <Cond bool={type==="purchase-order"}>
          <Field label="Vendedor de preferencia" bind={[seller, setSeller]} />
          <Radio legend="Condiciones de venta" options={["Al contado", "Cuenta corriente", "Cheque", "Pagaré"]}
          bind={[conditions, setConditions]} />
          <DateInput label="Fecha de preferencia "value={preferredDate} onChange={setPreferredDate} nonPast={true} />
          <Field label="Lugar de entrega" bind={[dispatchPlace, setDispatchPlace]} />
          <Field label="Transportista" bind={[deliverer, setDeliverer]} />
        </Cond>
      </Section>


      <Submit text="Generar" />
    </Form>
  );
}

/**Encuentra un título apropiado para el formulario.*/
function getTitle(type: string, flux: string): string {
  let title: string;
  switch (type) {
    case "purchase-order":  title = "Nueva orden de compra "; break;
    case "remittance":      title = "Nuevo remito "; break;
    case "invoice":         title = "Nueva factura "; break;
    case "debit-note":      title = "Nueva nota de débito "; break;
    case "credit-note":     title = "Nueva nota de crédito "; break;
    case "receipt-x":       title = "Nuevo recibo X "; break;
    case "receipt":         title = "Nuevo recibo simple "; break;
    case "promissory-note": title = "Nuevo pagaré "; break;
    case "check":           title = "Nuevo cheque "; break;
    case "other":           title = "Nueva operación "; break;
    default: return "Algo salió mal";
  }
  switch (flux) {
    case "in":  title += "de entrada"; break;
    case "out": title += "de salida"; break;
    default: return;
  }
  return title;
}

const PlusIcon: FC<{ link: string, cond: boolean }> = ({ link, cond }) => {
  return cond ?
  <Link to={link} style={{ flex: .5, marginTop: ".3rem", fontSize: "2rem", display: "block", textAlign: "center", color: "#fff" }}>
    <BiPlusCircle />
  </Link> : null
}

const Div: FC<{ children: ReactNode }> = ({children}) => {
  return (
    <div style={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
      {children}
    </div>
  )
}