import React, { useEffect, useState } from "react";
import { useNavigate }                from "react-router-dom";

import branch from "pages/Branches/models/branch";

import deleteBranch                   from '../../services/deleteBranch';

import { Button, Message }            from "components/formComponents";
import { Confirm }                    from "components/wrappers";
import './BranchBanner.css';


/**Encabezado con un información de la sucursal, y botones para eliminar y modificarla. */
export default function BranchBanner({branch}:{branch:branch}): JSX.Element {
    const [error, setError] = useState("");
    const navigate = useNavigate();
    
    function tryDeleteBranch() {
      deleteBranch(branch.ID, (ok:boolean, data:string)=> {
        if (ok) navigate(-1);
        else setError(data);
      })
    }


    return (
        <div data-component="branch-banner">
          <img src={branch.photo?.size>10?URL.createObjectURL(branch.photo):null} alt="" />
          <h2>{branch.name}</h2>
          <h3>{branch.address.city + ' ' + branch.address.street + ' ' + branch.address.addressNumber}</h3>

          <div>
          <Button onClick={()=>navigate('./editar')}>Editar</Button>
          <Confirm label="La instalación y los puntos de venta serán eliminados. Las operaciones se conservarán." onConfirm={tryDeleteBranch}>
            <Button type="delete">Eliminar</Button>
          </Confirm>
          </div>

          <Message type="error" message={error} />
        </div>
    );
}