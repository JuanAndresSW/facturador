import React, { useEffect, useState } from "react";

import postPointOfSale from '../../services/postPointOfSale';
import getPointsOfSale from "../../services/getPointsOfSale";
import deletePointOfSale from "../../services/deletePointOfSale";

import { pointsOfSale } from '../../models/pointsOfSale';

import { Message } from "components/formComponents";
import { Loading, Pagination } from "components/standalone";
import { BsFillXCircleFill } from "react-icons/bs";
import {Confirm, FlexDiv} from "components/wrappers";

import { toFourDigitNumber } from "utilities/conversions";
import './BranchPoints.css';


/**Muestra una lista de puntos de venta de una sucursal. Permite crear y eliminar puntos de venta. */
export default function BranchPoints({IDBranch}:{IDBranch:number}): JSX.Element {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    const [points, setPoints]:
    [pointsOfSale, React.Dispatch<React.SetStateAction<pointsOfSale>>]  = useState();
    const [page, setPage] = useState(0);

    useEffect(loadPointsOfSaleList, [page]);

    function newPointOfSale(): void {
        setLoading(true);
        postPointOfSale(IDBranch, (ok:boolean)=>{
            if (ok) loadPointsOfSaleList();
            else setError("Error al crear nuevo punto de venta.");
        });
        setLoading(false);
    }

    function loadPointsOfSaleList() {
        getPointsOfSale(IDBranch, page, (ok:boolean, list:pointsOfSale) => {
            if (ok) setPoints(list);
        })
    }

    function requestDeletePointOfSale(IDPoint:number, index:number): void {
        deletePointOfSale(IDPoint, (ok:boolean, error:string) => {
            if (ok) loadPointsOfSaleList();
            else setError(error);
        });
    }

    return (
        <div data-branchpoints>
            <h1>Puntos de venta</h1>

            {!points?null:
                
            <div style={{width:"70%"}}>

                <FlexDiv>

                    <Pagination page={page} setPage={setPage} totalPages={points.totalPages} last={points.last}/>
                    {loading? <Loading /> :

            
                    <div className="point-plus" onClick={newPointOfSale}>
                        +
                    </div>}
                    
                </FlexDiv>

                <FlexDiv>

                    {points.content.map((point, index)=>
                        <div className="point" key={index}>

                            <h3>N°{toFourDigitNumber(point.pointOfSaleNumber)}</h3>

                            <Confirm label={`El punto de venta ${toFourDigitNumber(point.pointOfSaleNumber)} será eliminado, las operaciones se conservarán. ¿Estás seguro?`}
                            onConfirm={()=>requestDeletePointOfSale(point.pointOfSaleId, index)}>
                                <BsFillXCircleFill />
                            </Confirm>
                        </div>
                    )}

                </FlexDiv>

                <Message type="error" message={error}/>
            </div>}
            
        </div>
    );
}

