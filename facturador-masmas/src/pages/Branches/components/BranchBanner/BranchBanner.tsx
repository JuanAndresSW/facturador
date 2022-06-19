import React, { useEffect, useState } from "react";
import { useNavigate }                from "react-router-dom";

import {branchesContent}              from '../../models/branches';
import { base64ToBlob }               from "utilities/conversions";
import deleteBranch                   from '../../services/deleteBranch';

import { Button, Message }            from "components/formComponents";
import { Confirm }                    from "components/wrappers";
import './BranchBanner.css';

/**Encabezado con un información de la sucursal, y botones para eliminar y modificarla. */
export default function BranchBanner({branch}:{branch:branchesContent}): JSX.Element {
    const [photo, setPhoto] = useState(undefined);
    const [error, setError] = useState("");
    const navigate = useNavigate();
    
    function tryDeleteBranch() {
      deleteBranch(branch.branchId, (ok:boolean, data:string)=> {
        if (ok) {
          navigate(-1);
          window.location.reload();
        }
        else setError(data);
      })
    }

    useEffect(()=>{
        base64ToBlob(branch.photo).then(logoAsBlob=>{
        setPhoto(URL.createObjectURL(logoAsBlob));
        });
    }, []);

    return (
        <div data-component="branch-banner">
          <img src={photo?photo:null} alt="" />
          <h2>{branch.name}</h2>
          <h3>{branch.locality + ' ' + branch.street + ' ' + branch.addressNumber}</h3>
          <div>
          <Button onClick={()=>navigate('./editar')}>Editar</Button>
          <Confirm label="La instalación y los puntos serán eliminados. Las operaciones se conservarán." onConfirm={tryDeleteBranch}>
            <Button type="delete">Eliminar</Button>
          </Confirm>
          </div>
          <Message type="error" message={error} />
        </div>
    );
}