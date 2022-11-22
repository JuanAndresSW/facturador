import React, { useEffect, useState } from "react";
import NewPoint from './NewBranch';
//Componentes.
import ManageBranch from './ManageBranch';
import BranchPreviewItem from "./components/BranchPreviewItem/BranchPreviewItem";
//Servicios.
import getBranches from './services/getBranches';
//Tipos.
import branch from './models/branch';
import listOfBranches from './models/listOfBranches';
//GUI.
import { Pagination, Plus } from "components/standalone";
import { Section, FlexDiv } from "components/wrappers";
import { Route, Routes, useNavigate } from "react-router-dom";
import { Dropdown } from "components/formComponents";
import { BiRefresh } from "react-icons/bi";


const sortOptions = [
  {name: "Ordenar por fecha",           value: "createdAt"},
  {name: "Ordenar por nombre",          value: "name"},
  {name: "Ordenar por nombre de calle", value: "street"}
]

/**Muestra una lista de sucursales con opciones para crear, ver y editar. */
export default function Branches(): JSX.Element {

  const navigate = useNavigate();

  //Lista de sucursales.
  const [branches, setBranches]:
  [branch[], React.Dispatch<React.SetStateAction<branch[]>>] = useState([]);

  //Sucursal seleccionada.
  //TODO: hacer que funcione sin estos dos valores, tomando en su lugar los datos de la URL.
  const [selectedBranch, setSelectedBranch]:
  [branch, React.Dispatch<React.SetStateAction<branch>>] = useState(undefined);
  const [selectedBranchID, setSelectedBranchID] = useState(-1);

  //Datos de paginación.
  const [page, setPage] =             useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [last, setLast] =             useState(true);
  const [sortBy, setSortBy]: [("createdAt"|"name"|"street"), React.Dispatch<React.SetStateAction<("createdAt"|"name"|"street")>>] 
  = useState("createdAt");

  //Recuperar la lista de sucursales.
  useEffect(requestListOfBranches, [page, sortBy]);

  function requestListOfBranches(): void {
    getBranches(page, sortBy, "asc").then(response => {
      if (response.ok) {
        const listOfBranches: listOfBranches = response.content;
        setBranches   (listOfBranches.branches);
        setTotalPages (listOfBranches.totalPages);
        setLast       (listOfBranches.last); 
      }
    });
  }

  /**Pantalla de selección de opciones de punto de venta. */
  const selectionScreen = 
  <Section label="Administrar instalaciones y puntos de venta">
    {!branches? null :
      <FlexDiv justify="flex-start">
        <Pagination page={page} setPage={setPage} totalPages={totalPages} last={last} />
        <Dropdown options={sortOptions} value={sortBy} onChange={setSortBy}/>
        <BiRefresh onClick={requestListOfBranches} style={{color:"#888", fontSize:"1.8rem", cursor:"pointer"}}/>
      </FlexDiv>
    }
    <FlexDiv justify="flex-start" align="flex-start">

      <Plus link="./nuevo" />
  
      {branches?.map((branch, index) => 
      <BranchPreviewItem
        key={index}
        title={branch.name}
        subtitle={branch.address.city + ' ' + branch.address.street + ' ' + branch.address.addressNumber}
        image={branch.photo}
        onClick={() => viewBranch(branch)}
      />)}
          
    </FlexDiv>
  </Section>;



  function viewBranch(branch: branch) {
    setSelectedBranchID(branch.ID);
    setSelectedBranch(branch);
    navigate(''+branch.ID);
  }

  return (
    <Routes>
      <Route index                            element={selectionScreen} />
      <Route path={"/nuevo"}                  element={<NewPoint />} />
      <Route path={''+selectedBranchID+'/*'}  element={<ManageBranch branch={selectedBranch} />} />
    </Routes>
  );
}