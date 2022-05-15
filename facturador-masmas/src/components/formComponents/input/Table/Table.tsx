import React from "react";
import { BsFileMinusFill, BsFillPlusSquareFill } from "react-icons/bs";
import DateTime from "../DateTime/DateTime";
import Field from "../Field/Field";
import './Table.css';

type props = {
    label?: string;
    headers: {th:string, type? :string}[];
    maxRows: number;
    bind: [string[][], React.Dispatch<React.SetStateAction<string[][]>>];
}

/**
 * Una tabla cuyas celdas son campos de texto o numéricos. Permite agregar y eliminar filas.
 * @param props.label - El título de la tabla.
 * @param props.headers - Array de encabezados de columnas. Expresa td: el título, y number: si es un campo numérico.
 * @param props.maxRows - Número máximo de filas permitidas.
 * @param props.bind - Array desestructurado asociado al valor del input. El estado (0) es un array bidimensional, 
 * con cada sub-array siendo una fila con la misma longitud que la expresada en headers.
 * @example 
 * headers = [{label:"foo"}, {label:"bar", number:true}];
 * bind = [table, setTable] = useState([["", "0"]]); //dos columnas y una fila
 */
export default function Table({ label = "", headers, maxRows, bind }: props): JSX.Element {

    /**Actualiza el valor de una celda de la tabla */
    function setter(rowIndex:number, colIndex:number, value:string) {
        //Ignorar si la celda es numérica y el número es negativo.
        if (headers[colIndex].type==="number" && parseInt(value) < 1) return;
        if (value.length > 20) return;
        //Hacer una shallow copy y modificarla para forzar re-renderizado.
        const table = [...bind[0]];
        table[rowIndex][colIndex] = headers[colIndex].type==="number"? parseInt(value).toString() : value;
        bind[1](table);
    }

    function addRow() {
        const table = [...bind[0]];
        table.push(headers.map(()=>""));
        bind[1](table);
    }
    function removeRow() {
        const table = [...bind[0]];
        table.pop();
        bind[1](table);
    }

    return (
        <>
        <legend>{label}</legend>
        <table>
            <thead>
            <tr>
                {headers.map((header, index) => 
                    <th key={index}>{header.th}</th>
                )}
            </tr>
            </thead>

            <tbody>
                {bind[0].map((row, rowIndex) => 
                    <tr key={rowIndex}>
                        {
                        //Slice es usado para no exceder el número de columnas.
                        row.slice(0, headers.length).map((cell, colIndex) =>
                        <td key={colIndex}>
                            {
                            headers[colIndex].type === "date" ?
                            (
                                <DateTime value={cell} onChange={(value:string)=>
                                setter(rowIndex, colIndex, value)} />
                            )
                            :
                            (
                                <Field 
                                type={headers[colIndex].type === "number"?"number":"text"}
                                bind={
                                [cell,
                                (value:string)=>setter(rowIndex, colIndex, value)]
                                } />
                            )
                            
                                
                            }
                            </td>
                        )}
                        {(rowIndex+1 === bind[0].length && rowIndex>0) ? 
                        <td onClick={removeRow}><BsFileMinusFill /></td> : null}
                    </tr>
                )}
                
            </tbody>


            {bind[0].length < maxRows ?
            <tfoot>
                <tr>
                {
                    headers.map((h, index)=>
                        (index+1 === headers.length)?
                        <td key={h.th}>
                            <BsFillPlusSquareFill onClick={addRow} />
                        </td>
                        :<td key={h.th} />
                    )
                }
                </tr>
            </tfoot>
            : null}

        </table>
        </>
    );
};