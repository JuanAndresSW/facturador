import React from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import {BiChevronLeft, BiHistory,BiLogInCircle,BiLogOutCircle,BiTransferAlt,} from "react-icons/bi";
import {Section, Option} from 'components/layout';
import DocumentForm from "./OperationForm";


export default function Operation() {
  return (
    <Routes>
      <Route index                element={OperationSelectionScreen} />
      <Route path={"/enviar/*"}   element={<Documents flux="out" />} />
      <Route path={"/recibir/*"}  element={<Documents flux="in" />} />
      <Route path={"/registro"}   element={<>REGISTRO</>} />
      <Route path={"/historial"}  element={<>HISTORIAL</>} />
    </Routes>
  );
}

const OperationSelectionScreen = (
  
  <>
    <Section label="Nuevo documento">
      <Option label="Enviar" icon={<BiLogOutCircle />} link="./enviar" />
      <Option label="Recibir" icon={<BiLogInCircle />} link="./recibir" />
    </Section>

    <Section label="Otras operaciones">
      <Option label="Registar un egreso o ingreso" icon={<BiTransferAlt />} link="./registro" />
      <Option label="Ver o continuar con una transacción" icon={<BiHistory />} link="./historial" />
    </Section>
  </>
);


//Pantalla de selección de tipos de documentos.
function Documents({ flux }: {flux:("in"|"out")}): JSX.Element {
  const navigate = useNavigate();
  return (
    <>
    <BiChevronLeft onClick={() => navigate(-1)} style={{margin:"1rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />
    <Routes>
      <Route index                      element={documentsSelectionScreen} />
      <Route path={"/factura"}          element={<DocumentForm flux={flux} type="invoice" />} />
      <Route path={"/nota-de-credito"}  element={<DocumentForm flux={flux} type="credit-note" />} />
      <Route path={"/nota-de-debito"}   element={<DocumentForm flux={flux} type="debit-note" />} />
      <Route path={"/recibo-x"}         element={<DocumentForm flux={flux} type="receipt-x" />} />
      <Route path={"/recibo"}           element={<DocumentForm flux={flux} type="receipt" />} />
      <Route path={"/orden-de-compra"}  element={<DocumentForm flux={flux} type="purchase-order" />} />
      <Route path={"/remito"}           element={<DocumentForm flux={flux} type="remittance" />} />
      <Route path={"/cheque"}           element={<DocumentForm flux={flux} type="check" />} />
      <Route path={"/pagare"}           element={<DocumentForm flux={flux} type="promissory-note" />} />
    </Routes></>
  );
}

const documentsSelectionScreen = (
  <Section>
    <Option label="Factura"         link="./factura" />
    <Option label="Nota de crédito" link="./nota-de-credito" />
    <Option label="Nota de débito"  link="./nota-de-debito" />
    <Option label="Recibo X"        link="./recibo-x" />
    <Option label="Recibo"          link="./recibo" />
    <Option label="Orden de compra" link="./orden-de-compra" />
    <Option label="Remito"          link="./remito" />
    <Option label="Pagaré"          link="./pagare" />
    <Option label="Cheque"          link="./cheque" />
  </Section>
);