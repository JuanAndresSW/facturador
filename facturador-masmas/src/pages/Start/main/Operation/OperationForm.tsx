//React.
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

//Componentes del formulario.
import { DateTime, ErrorMessage, Field, Form, Radio, Select, Submit, Switch, Table, Textarea } from "components/formComponents";
import { Section, Cond, FlexDiv } from 'components/layout'
import { BiChevronsDown, BiChevronsUp, BiGroup, BiPlusCircle, BiUser } from "react-icons/bi";

//Elementos de documento generado.
import { Invoice, CreditNote, DebitNote, Receipt, PurchaseOrder, Remittance, Check, PromissoryNote, } from "./documents";

//Utilidades.
import getDocumentTitle from 'utils/getDocumentTitle';

//Servicios.
import PointOfSale from 'services/PointOfSale';
import Partner from 'services/Partner';
import Group from 'services/Group';

//Tipos.
type props = {
  flux: "in" | "out";
  type: ("purchase-order" | "remittance" | "invoice" | "debit-note" | "credit-note" | "receipt-x" | "receipt" | "promissory-note" | "check" | "other");
};

/**
 * Devuelve un formulario que recolecta los datos necesitados por el back-end para generar un documento comercial.
 * @param flux Flujo de emisión del documento. Valores aceptados: 'in' | 'out'.
 * @param type Tipo de documento comercial en inglés y kebab-case.
 */
export default function OperationForm({ flux, type }: props): JSX.Element {

  //Solicitar los datos a mostrar en el primer renderizado.
  useEffect(() => {

    PointOfSale.getArray((state:number,data:string)=>{
      if (state===200) setDisplayPointsOfSale(JSON.parse(data));
    });
    Partner.getArray((state:number,data:string)=>{
      if (state===200) setDisplayPartners(JSON.parse(data));
    });
    Group.getArray((state:number,data:string)=>{
      if (state===200) setDisplayGroups(JSON.parse(data));
    });

  }, []);

  // #### Los datos del servidor necesarios para mostrar el formulario. #### //
  const [displayPointsOfSale, setDisplayPointsOfSale] = useState(undefined);
  const [displayPartners, setDisplayPartners] = useState(undefined);
  const [displayGroups, setDisplayGroups] = useState(undefined);
  const [displayRoot, setDisplayRoot] = useState(false);

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
  //Recibo x y recibo
  const [payer, setPayer] =                 useState("");
  const [payerAddress, setPayerAdress] =    useState("");
  const [paymentTime, setPaymentTime] =     useState("");
  //Recibo x.
  const [paymentMethods, setPaymentMethods] =       useState([["", "", ""]]);
  const [paymentImputation, setPaymentImputation] = useState([["", "", "", ""]]);
  const [detailOfValues, setDetailOfValues] =       useState([["", "", "", "", ""]]);
  //Orden de compra.
  const [seller, setSeller] =               useState("");
  const [conditions, setConditions] =       useState("");
  const [preferredDate, setPreferredDate] = useState("");
  const [dispatchPlace, setDispatchPlace] = useState("");
  const [deliverer, setDeliverer] =         useState("");

  //Mensaje de error al generar el documento.
  const [error, setError] = useState("hello world");


  // #### El formulario #### //
  return (
    <Form title={getDocumentTitle(type, flux)}>

      <ErrorMessage message={error} />

      <Section label="Partícipes">

        <FlexDiv>
          <Select options={displayPointsOfSale} bind={[pointOfSale, setPointOfSale]}
            fallback={displayRoot ? "No tienes ningún punto de venta. Crea tu primero:" : ""} />
          <Cond bool={displayRoot}><PlusIcon link={"/"} /></Cond>
        </FlexDiv>

        <Cond bool={flux==="in"}>
          <BiChevronsUp style={{ margin: "1.2rem auto", display: "block", cursor: "default", fontSize: "2rem", color: "white" }} />
        </Cond>

        <Cond bool={flux==="out"}>
          <BiChevronsDown style={{ margin: "1.2rem auto", display: "block", cursor: "default", fontSize: "2rem", color: "white" }} />
        </Cond>

        <Switch falseIcon={<BiUser />} trueIcon={<BiGroup />} bind={[useGroup, setUseGroup]} />

        <FlexDiv>
          <Select
            options={useGroup ? displayGroups : displayPartners}
            bind={useGroup ? [group, setGroup] : [partner, setPartner]}
            fallback={useGroup ? "No tienes ningún grupo. Crea tu primero:" : "No tienes ningún socio. Crea tu primero:"}
          />
          <PlusIcon link="/" />
        </FlexDiv>

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

        <Cond bool={("receipt-x"+"receipt").includes(type)}>
          <Field label="Pagador" bind={[payer, setPayer]} />
        </Cond>
        <Cond bool={("receipt-x").includes(type)}>
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
          <DateTime label="Fecha de preferencia "value={preferredDate} onChange={setPreferredDate} nonPast={true} />
          <Field label="Lugar de entrega" bind={[dispatchPlace, setDispatchPlace]} />
          <Field label="Transportista" bind={[deliverer, setDeliverer]} />
        </Cond>

        <Cond bool={("receipt-x"+"receipt").includes(type)}>
          <Field label="Domicilio de pago" bind={[payerAddress, setPayerAdress]} />
          <DateTime label="" type="time" value={paymentTime} onChange={setPaymentTime} />
        </Cond>
      </Section>


      <Submit text="Generar" />
    </Form>
  );
}


//# Los siguientes elementos son muy simples, por eso se decidió no moverlos a archivos separados. #//

/**
 * Un signo + con Link a la dirección especificada cuando la condición 'cond' es cumplida.
 * Valor null en caso contrario.
 */
const PlusIcon: React.FC<{ link: string }> = ({ link }) => {
  return (
  <Link to={link} style={{ flex: .5, marginTop: ".3rem", fontSize: "2rem", display: "block", textAlign: "center", color: "#fff" }}>
    <BiPlusCircle />
  </Link>)
}