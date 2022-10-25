import React from "react";
import { Route, Routes } from "react-router-dom";

import OperationForm from "./OperationForm";
import Document from "./Document";
import DocumentHistory from "./components/DocumentHistory/DocumentHistory";

import OperationSelectionItem from "./components/Option/OperationSelectionItem";
import { FlexDiv, Section } from 'components/wrappers';


/**Pantalla de selección de tipos de operaciones, con el historial de operaciones.*/
export default function OperationSelection() {
  return <Routes>
    <Route index                      element={OperationMainScreen} />
    <Route path={"/documento"}        element={<Document />}        />


    <Route path={"/factura"}          element={<OperationForm documentClassCode="Fa" />} />
    <Route path={"/nota-de-credito"}  element={<OperationForm documentClassCode="Nc" />} />
    <Route path={"/nota-de-debito"}   element={<OperationForm documentClassCode="Nd" />} />
    <Route path={"/recibo-x"}         element={<OperationForm documentClassCode="Rx" />} />
    <Route path={"/recibo"}           element={<OperationForm documentClassCode="Rs" />} />
    <Route path={"/orden-de-compra"}  element={<OperationForm documentClassCode="Oc" />} />
    <Route path={"/remito"}           element={<OperationForm documentClassCode="Rm" />} />
    <Route path={"/cheque"}           element={<OperationForm documentClassCode="Ch" />} />
    <Route path={"/pagare"}           element={<OperationForm documentClassCode="Pa" />} />
  </Routes>

}

const OperationMainScreen = <>
  <Section label="Nueva operación">
    <FlexDiv>
      <OperationSelectionItem   documentClassCode="Fa" link="./factura"          />
      <OperationSelectionItem   documentClassCode="Oc" link="./orden-de-compra" disabled  />
      <OperationSelectionItem   documentClassCode="Rm" link="./remito" disabled           />
      <OperationSelectionItem   documentClassCode="Rx" link="./recibo-x" disabled         />
      <OperationSelectionItem   documentClassCode="Rs" link="./recibo" disabled           />
      <OperationSelectionItem   documentClassCode="Nc" link="./nota-de-credito"  />
      <OperationSelectionItem   documentClassCode="Nd" link="./nota-de-debito"   />
      <OperationSelectionItem   documentClassCode="Pa" link="./pagare" disabled  />
      <OperationSelectionItem   documentClassCode="Ch" link="./cheque" disabled  />
    </FlexDiv>

      
  </Section>
  <DocumentHistory /> 
</>