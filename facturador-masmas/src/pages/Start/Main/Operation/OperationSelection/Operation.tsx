import React from "react";
import {
  BiHistory,
  BiLogInCircle,
  BiLogOutCircle,
  BiTransferAlt,
} from "react-icons/bi";
import Documents from "./Documents";
import "./Operation.css";
import { NavLink, Route, Routes } from "react-router-dom";

export default function Operation() {
  return (
    <Routes>
      <Route index element={start} />
      <Route path={"/enviar/*"} element={<Documents flux="out" />} />
      <Route path={"/recibir/*"} element={<Documents flux="in" />} />
      <Route path={"/registro"} element={<>REGISTRO</>} />
      <Route path={"/historial"} element={<>HISTORIAL</>} />
    </Routes>
  );
}

const start = (
  <div id="transaction-options">
    <label>Nuevo documento</label>

    <div className="section">
      <NavLink to={"./enviar"} className="option">
        <BiLogOutCircle />
        Enviar
      </NavLink>
      <NavLink to={"./recibir"} className="option">
        <BiLogInCircle />
        Recibir
      </NavLink>
    </div>

    <label>Otras operaciones</label>

    <div className="section">
      <NavLink to={"./registro"} className="option">
        <BiTransferAlt />
        Registar un egreso o ingreso
      </NavLink>
      <NavLink to={"./historial"} className="option">
        <BiHistory />
        Ver o continuar con una transacción
      </NavLink>
    </div>
  </div>
);
