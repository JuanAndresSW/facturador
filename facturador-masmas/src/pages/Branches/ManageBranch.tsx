import React from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import branch from './models/branch';
//UI.
import BranchBanner from "./components/BranchBanner/BranchBanner";
import BranchPoints from "./components/BranchPoints/BranchPoints";
import EditBranch from './EditBranch';
import { BiChevronLeft } from "react-icons/bi";



/**Menú para visualizar y cambiar datos de una sucursal y sus puntos de venta.*/
export default function ManageBranch({branch}:{branch:branch}): JSX.Element {

  const navigate = useNavigate();

  const manageBranch = (
    <>
    <BranchBanner branch={branch} />
    <BranchPoints IDBranch={branch.ID} />
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