import React, { Suspense, useEffect, useState } from "react";
import NewPoint from './NewBranch';

//Componentes.
import ManageBranch from './ManageBranch';

//Servicios.
import getBranches from './services/getBranches';

//Tipos.
import branches, {branchesContent} from './models/branches';

//GUI.
import { Loading, OptionWithPhoto } from "styledComponents";
import { Section, Plus, FlexDiv } from "styledComponents";
import { Route, Routes, useNavigate } from "react-router-dom";
import { MdModeEdit } from "react-icons/md";


/**Muestra opciones para crear, ver y editar sucursales. */
export default function Branches(): JSX.Element {

  const navigate = useNavigate();

  //Lista de sucursales.
  const [branches, setBranches]:
  [branches, React.Dispatch<React.SetStateAction<branches>>] = useState(undefined);
  //Sucursal seleccionada.
  const [branch, setBranch]:
  [branchesContent, React.Dispatch<React.SetStateAction<branchesContent>>] = useState(undefined);
  //Id de la sucursal seleccionada.
  const [branchID, setBranchID] = useState(-1);

  //Recuperar la lista de sucursales.
  useEffect(()=>{
    getBranches(async (ok:boolean, content: branches)=>{
      if (ok) setBranches(content);
      else console.log('error');
    });
  }, []);

  

  /**Pantalla de selección de opciones de punto de venta. */
  const selectionScreen = (
    <Section label="Administrar instalaciones y puntos de venta">
      <FlexDiv justify="flex-start">

        {
          !branches? <Loading/>:

          <>
          {
            branches.content.map((branch, index) => 
            <OptionWithPhoto
              key={index}
              title={branch.name}
              subtitle={branch.locality + ' ' + branch.street + ' ' + branch.numberAddress}
              image={branch.photo.length>10?branch.photo:null}
              onClick={() => viewBranch(branch)}
               />
            )
            
          }
            
            <Plus link="./nuevo" />
          
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
      <Route path={''+branchID+'/*'}  element={<ManageBranch branch={branch} />} />
    </Routes>
  );
}