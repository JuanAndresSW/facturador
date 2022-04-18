import React, { useEffect, useState } from "react";
import NewPoint from './NewBranch';
import BranchOptions from './BranchOptions';

//GUI.
import { OptionWithPhoto } from "styledComponents";
import { Section, Plus, FlexDiv } from "styledComponents";
import { Route, Routes, useNavigate } from "react-router-dom";
import { MdModeEdit } from "react-icons/md";
import getUserAvatar from "pages/Home/services/getUserAvatar";


//ID de la sucursal elegida para mostrar en detalle.
//let branchID = -1;
//function setBranchID(ID:number) { branchID = ID }

/**Muestra opciones para crear, ver y editar sucursales. */
export default function Points(): JSX.Element {

  //PRUEBA DE IMAGEN.
  const [branchID, setBranchID] = useState(-1);

  //PRUEBA DE IMAGEN.
  const [testImg, setTestImage] = useState(undefined);
  useEffect(()=>getUserAvatar((int:number, data: File)=> setTestImage(data)), []);

  const navigate = useNavigate();

  /**Pantalla de selección de opciones de punto de venta. */
  const selectionScreen = (
  <>
    <Section label="Administrar instalaciones y puntos de venta">
      <FlexDiv justify="space-around">

        <OptionWithPhoto 
        title="testalalsllalallallalalallallalalallalalalallalalalal"
        subtitle="testsub" 
        image={undefined}
        onClick={viewBranch}
        option={{icon:<MdModeEdit/>, onClick:editBranch}}
        ID={777} />


        <OptionWithPhoto 
        title="testalalsllalallallalalallallalalallalalalallalalalal"
        subtitle="testsub" 
        image={testImg}
        onClick={viewBranch}
        option={{icon:<MdModeEdit/>, onClick:editBranch}}
        ID={888} />

        <Plus link="./nuevo" />

      </FlexDiv>
    </Section>
  </>
  );

  function viewBranch(ID: number) {
    setBranchID(ID);
    navigate('./administrar-'+ID);
  }
  function editBranch(ID: number) {
    setBranchID(ID);
    navigate('./editar-'+ID)
  }

  return (
    <Routes>
      <Route index                            element={selectionScreen} />
      <Route path={"/nuevo"}                  element={<NewPoint />} />
      <Route path={"/editar-"+branchID}       element={<>Editar datos de rama ID {branchID}</>} />
      <Route path={"/administrar-"+branchID}  element={<>Ver y administrar puntos de venta de rama ID {branchID}</>} />
    </Routes>
  );
}