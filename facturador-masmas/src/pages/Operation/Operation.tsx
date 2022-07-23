import { Option } from "components/standalone";
import { FlexDiv, Section } from 'components/wrappers';
import React from "react";
import { Route, Routes } from "react-router-dom";
import OperationForm from "./OperationForm";

/**Pantalla de selección de tipos de operaciones, con el historial de operaciones.*/
export default function Operation() {
  return (
    <Routes>
      <Route index                      element={OperationMainScreen} />
      <Route path={"/factura"}          element={<OperationForm type="Fa" />} />
      <Route path={"/nota-de-credito"}  element={<OperationForm type="Nc" />} />
      <Route path={"/nota-de-debito"}   element={<OperationForm type="Nd" />} />
      <Route path={"/recibo-x"}         element={<OperationForm type="Rx" />} />
      <Route path={"/recibo"}           element={<OperationForm type="Rs" />} />
      <Route path={"/orden-de-compra"}  element={<OperationForm type="Oc" />} />
      <Route path={"/remito"}           element={<OperationForm type="Rm" />} />
      <Route path={"/cheque"}           element={<OperationForm type="Ch" />} />
      <Route path={"/pagare"}           element={<OperationForm type="Pa" />} />
      <Route path={"/variacion"}        element={<OperationForm type="Va" />} />
    </Routes>
  );
}

const OperationMainScreen = (
  
  <>
    <Section label="Nueva operación">
      <FlexDiv wrap={false} justify="flex-start">
        <Option name="Factura"          label="Fa" link="./factura"         color="#fa1" />
        <Option name="Orden de compra"  label="Oc" link="./orden-de-compra" color="#248" />
        <Option name="Remito"           label="Rm" link="./remito"          color="#841" />
        <Option name="Recibo X"         label="Rx" link="./recibo-x"        color="purple" />
        <Option name="Recibo simple"    label="Rs" link="./recibo"          color="#299" />
        <Option name="Nota de crédito"  label="Nc" link="./nota-de-credito" color="#285" />
        <Option name="Nota de débito"   label="Nd" link="./nota-de-debito"  color="hotpink" />
        <Option name="Pagaré"           label="Pa" link="./pagare"          color="842" />
        <Option name="Cheque"           label="Ch" link="./cheque"          color="green" />
        <Option name="Variación"        label="Va" link="./variacion"       color="gray" />
      </FlexDiv>
    </Section>

    <div>


    </div>
  </>
);