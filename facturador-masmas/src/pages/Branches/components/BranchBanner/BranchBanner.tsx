import React, { useEffect, useState } from "react";
import {branchesContent} from '../../models/branches';
import { base64ToBlob } from "utilities/conversions";
import './BranchBanner.css';
import { Button } from "components/formComponents";
import { useNavigate } from "react-router-dom";
import FlexDiv from "styledComponents/FlexDiv";

export default function BranchBanner({branch}:{branch:branchesContent}): JSX.Element {
    const [photo, setPhoto] = useState(undefined);
    const navigate = useNavigate();
    

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
          <Button type="delete" text="Eliminar" />
          </div>
        </div>
    );
}