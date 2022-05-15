import React, { useState } from "react";
import {branchesContent} from '../../models/branches';
import { BsFillXCircleFill } from "react-icons/bs";
import './BranchPoints.css';
import { Table } from "components/formComponents";
import FlexDiv from "styledComponents/FlexDiv";

export default function BranchPoints(): JSX.Element {

    return (
        <div data-component="BranchPoints">
            <h1>Puntos de venta</h1>

            <FlexDiv>
                <div className="point-list">
                    <h3>N°999</h3>

                    <BsFillXCircleFill />
                </div>
            </FlexDiv>

            <div className="point-plus">
                +
            </div>

            
                
                {/* <h2>N°</h2>
                <h2>piso</h2>
                <h2>unidad</h2>

                <p>999</p>
                <p>2</p>
                <p>1</p>

                <p>999</p>
                <p>2</p>
                <p>1</p> */}
        </div>
    );
}