import React, { useState } from "react";
import {branchesContent} from '../../models/branches';
import { BsFillXCircleFill } from "react-icons/bs";
import './BranchPoints.css';
import { Table } from "components/formComponents";
import FlexDiv from "styledComponents/FlexDiv";

export default function BranchPoints(): JSX.Element {

    function deletePointOfSale() {
        window.confirm("¿Está seguro de que quiere eliminar este punto de venta?\nEsta acción no puede ser revertida.");
    }

    return (
        <div data-component="BranchPoints">
            <h1>Puntos de venta</h1>

            <FlexDiv>
                <div className="point-list">
                    <h3>N°999</h3>

                    <BsFillXCircleFill onClick={deletePointOfSale} />
                </div>
            </FlexDiv>

            <div className="point-plus">
                +
            </div>

        </div>
    );
}