import React, { useEffect, useReducer, useState } from "react";
import NewPoint from './NewBranch';

//Componentes.
import ManageBranch from './ManageBranch';

//Servicios.
import getBranches from './services/getBranches';

//Tipos.
import branches, {branchesContent} from './models/branches';

//GUI.
import { Loading, OptionWithPhoto, Pagination, Plus } from "components/standalone";
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
  const [updater, render] = useReducer(x => x + 1, 0);

  //Lista de sucursales.
  const [branches, setBranches]:
  [branches, React.Dispatch<React.SetStateAction<branches>>] = useState(undefined);
  //Sucursal seleccionada.
  const [branch, setBranch]:
  [branchesContent, React.Dispatch<React.SetStateAction<branchesContent>>] = useState(undefined);
  //Id de la sucursal seleccionada.
  const [branchID, setBranchID] = useState(-1);
  const [page, setPage] = useState(0);
  const [sortBy, setSortBy] = useState("createdAt");

  //Recuperar la lista de sucursales.
  useEffect(requestBranches, [page, sortBy]);

  function requestBranches(): void {
    getBranches(page, sortBy, "asc", (ok:boolean, content: branches)=>{
      if (ok) setBranches(content);
    });
  }

  

  /**Pantalla de selección de opciones de punto de venta. */
  const selectionScreen = (
    <Section label="Administrar instalaciones y puntos de venta">
      {!branches? null :
        <FlexDiv justify="flex-start">
          <Pagination page={page} setPage={setPage} totalPages={branches.totalPages} last={branches.last} />
          <Dropdown options={sortOptions} value={sortBy} onChange={setSortBy}/>
          <BiRefresh onClick={requestBranches} style={{color:"#888", fontSize:"1.8rem", cursor:"pointer"}}/>
        </FlexDiv>
      }
      <FlexDiv justify="flex-start" align="flex-start">


        {
          !branches? <Loading/>:

          <>

          <Plus link="./nuevo" />
          
          {
            branches.content.map((branch, index) => 
            <OptionWithPhoto
              key={index}
              title={branch.name}
              subtitle={branch.locality + ' ' + branch.street + ' ' + branch.addressNumber}
              image={branch.photo.length>10?branch.photo:null}
              onClick={() => viewBranch(branch)}
               />
            )
            
          }
          
          </>
        }

      </FlexDiv>
    </Section>
  );

  function viewBranch(branch: branchesContent) {
    setBranchID(branch.branchId);
    setBranch(branch);
    navigate(''+branch.branchId);
  }

  return (
    <Routes>
      <Route index                            element={selectionScreen} />
      <Route path={"/nuevo"}                  element={<NewPoint />} />
      <Route path={''+branchID+'/*'}          element={<ManageBranch branch={branch} />} />
    </Routes>
  );
}