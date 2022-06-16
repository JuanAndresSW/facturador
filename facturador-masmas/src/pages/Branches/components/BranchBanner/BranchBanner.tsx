import React, { useEffect, useState } from "react";
import {branchesContent}              from '../../models/branches';
import { base64ToBlob }               from "utilities/conversions";
import './BranchBanner.css';
import { Button, Message }            from "components/formComponents";
import { useNavigate }                from "react-router-dom";
import deleteBranch                   from '../../services/deleteBranch';
import { Confirm }                    from "components/layout";

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
          <h3>{branch.locality + ' ' + branch.street + ' ' + branch.numberAddress}</h3>
          <div>
          <Button text="Editar" onClick={()=>navigate('./editar')} />
          <Confirm label="La instalación y los puntos serán eliminados. Las operaciones se conservarán." onConfirm={tryDeleteBranch}>
            <Button type="delete" text="Eliminar" />
          </Confirm>
          </div>
          <Message type="error" message={error} />
        </div>
    );
}