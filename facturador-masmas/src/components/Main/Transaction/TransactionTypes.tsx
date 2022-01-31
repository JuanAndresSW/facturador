import React from "react";
import { NavLink, Route, Routes } from "react-router-dom";
import DocumentForm from "./DocumentForm/DocumentForm";
import "./Transaction.css";

type props = {
  flux: string;
};

export default function TransactionTypes({ flux }: props): JSX.Element {
  return (
    <Routes>
      <Route index element={start} />
      <Route
        path={"/factura"}
        element={<DocumentForm flux={flux} type="invoice" />}
      />
      <Route
        path={"/nota-de-credito"}
        element={<DocumentForm flux={flux} type="credit-note" />}
      />
      <Route
        path={"/nota-de-debito"}
        element={<DocumentForm flux={flux} type="debit-note" />}
      />
      <Route
        path={"/recibo-x"}
        element={<DocumentForm flux={flux} type="receipt-x" />}
      />
      <Route
        path={"/recibo"}
        element={<DocumentForm flux={flux} type="receipt" />}
      />
      <Route
        path={"/orden-de-compra"}
        element={<DocumentForm flux={flux} type="purchase-order" />}
      />
      <Route
        path={"/remito"}
        element={<DocumentForm flux={flux} type="remit" />}
      />
      <Route
        path={"/cheque"}
        element={<DocumentForm flux={flux} type="check" />}
      />
      <Route
        path={"/pagare"}
        element={<DocumentForm flux={flux} type="promissory-note" />}
      />
    </Routes>
  );
}

const start = (
  <div id="transaction-options">
    <div className="section">
      <NavLink to={"./factura"} className="option">
        Factura
      </NavLink>
      <NavLink to={"./nota-de-credito"} className="option">
        Nota de crédito
      </NavLink>
      <NavLink to={"./nota-de-debito"} className="option">
        Nota de débito
      </NavLink>
      <NavLink to={"./recibo-x"} className="option">
        Recibo X
      </NavLink>
      <NavLink to={"./recibo"} className="option">
        Recibo
      </NavLink>
      <NavLink to={"./orden-de-compra"} className="option">
        Orden de compra
      </NavLink>
      <NavLink to={"./remito"} className="option">
        Remito
      </NavLink>
      <NavLink to={"./cheque"} className="option">
        Cheque
      </NavLink>
      <NavLink to={"./pagare"} className="option">
        Pagaré
      </NavLink>
    </div>
  </div>
);
