import React, { useEffect, useState } from "react";
import {branchesContent} from './models/branches';
//UI.
import BranchBanner from "./components/BranchBanner/BranchBanner";
import BranchPoints from "./components/BranchPoints/BranchPoints";
import EditBranch from './EditBranch';
import { Route, Routes, useNavigate } from "react-router-dom";
import { BiChevronLeft } from "react-icons/bi";



/**Formulario para visualizar y cambiar datos de una sucursal.
 * Precisa del parámetro ID para recuperar el logo.*/
export default function ManageBranch({branch}:{branch:branchesContent}): JSX.Element {

  const navigate = useNavigate();

  const manageBranch = (
    <>
    <BranchBanner branch={branch} />
    <BranchPoints />
    </>
  )

  return (
    <>
    <BiChevronLeft onClick={() => navigate(-1)} style={{margin:".2rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />
    <Routes>
      <Route index                            element={manageBranch} />
      <Route path={"/editar"}                 element={<EditBranch branch={branch} />} />
    </Routes>
    </>
  );
}