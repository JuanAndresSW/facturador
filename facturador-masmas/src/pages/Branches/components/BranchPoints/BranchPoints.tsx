import React, { useState } from "react";

import { Message } from "components/formComponents";
import postPointOfSale from '../../services/postPointOfSale';

import { Loading } from "styledComponents";
import { BsFillXCircleFill } from "react-icons/bs";
import FlexDiv from "styledComponents/FlexDiv";
import './BranchPoints.css';


/**Muestra una lista de puntos de venta de una sucursal. Permite crear y eliminar puntos de venta. */
export default function BranchPoints({branchID:number}:{branchID:number}): JSX.Element {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    function newPointOfSale(): void {
        setLoading(true);
        postPointOfSale((ok:boolean,data:string)=>{
            if (ok) loadPointsOfSaleList();
            else setError(data);
        });
        setLoading(false);
    }

    function loadPointsOfSaleList() {
        console.log("this should load all pointsofsale");
    }

    function deletePointOfSale(): void {
        window.confirm("¿Está seguro de que quiere eliminar este punto de venta?\nEsta acción no puede ser revertida.");
    }

    return (
        <div data-BranchPoints>
            <h1>Puntos de venta</h1>

            <FlexDiv>
                <div className="point-list">
                    <h3>N°999</h3>

                    <BsFillXCircleFill onClick={deletePointOfSale} />
                </div>
            </FlexDiv>

            {loading? <Loading /> :
            <div className="point-plus" onClick={newPointOfSale}>
                +
            </div>}

            <Message type="error" message={error}/>
        </div>
    );
}