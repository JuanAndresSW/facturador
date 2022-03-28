import React, { useState } from "react";
import NewPoint from './NewPoint';
import PointOptions from './PointOptions';

//GUI.
import { Button, ErrorMessage, Field, Form, Image, Radio } from "components/formComponents";
import { Retractable } from "components/layout";
import { Loading, Section, Option } from "styledComponents";
import { BiChevronLeft, BiPlusCircle } from "react-icons/bi";
import { Route, Routes } from "react-router-dom";

//ID del punto de venta elegido para mostrar en detalle.
let pointID:number = -1;

/**Muestra opciones para crear, ver y editar puntos de venta. */
export default function Points(): JSX.Element {

  return (
    <Routes>
      <Route index              element={selectionScreen} />
      <Route path={"/nuevo"}    element={<NewPoint />} />
      <Route path={"/opciones"} element={<PointOptions ID={pointID} />} />
    </Routes>
  );
}

/**Pantalla de selección de opciones de punto de venta. */
const selectionScreen = (
  <>
    <Section label="Nuevo punto">
      <Option label="Nuevo punto de venta" icon={<BiPlusCircle />} link={"./nuevo"}></Option>
    </Section>
    
    <Section label="Administrar puntos de venta">
      (para esto es necesario recuperar la lista de puntos de venta del comerciante)
    </Section>
  </>
);