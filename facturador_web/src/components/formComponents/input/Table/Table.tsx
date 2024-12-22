import React from "react";
import { BsFileMinusFill, BsFillPlusSquareFill } from "react-icons/bs";
import DateTime from "../DateTime/DateTime";
import Field from "../Field/Field";
import './Table.css';

type props = {
    label?: string,
    thead: {name:string, type? : string}[],
    tbody: (string|number)[][],
    onChange: Function,
    maxRows?: number
}

/**
 * Una tabla cuyas celdas son campos de texto o numéricos. Permite agregar y eliminar filas.
 * @param props.label    - El título de la tabla.
 * @param props.thead    - Lista de encabezados de columnas. Expresa el nombre y el tipo de input. Por dedefecto es de tipo texto.
 * @param props.tbody    - array bidimensional, con cada array interno siendo una columna, habiendo la misma cantidad de elementos que la expresada en thead.
 * @param props.onChange - Función a la cual se argumenta los nuevos valores de la tabla al momento de un cambio. 
 * @param props.maxRows  - Número máximo de filas permitidas. Por defecto es 1.
 * @example 
 * thead = [{name:"foo"}, {name:"bar", type:"date"}];
 * tbody = [[""][0]]; //Dos columnas y una fila.
 */
export default function Table({ label="", thead, tbody, onChange, maxRows=1 }: props): JSX.Element {

    function setCellValue(rowIndex:number, colIndex:number, value:string) {
        const newTable = [...tbody];
        newTable[colIndex][rowIndex] = value;
        onChange(newTable);
    }
    function addRow() {
        const newTable = [...tbody];
        newTable.forEach(column=>column.push(undefined));
        onChange(newTable);
    }
    function removeRow() {
        const newTable = [...tbody];
        newTable.forEach(column=>column.pop());
        onChange(newTable);
    }

    return (<>
    
        <legend>{label}</legend>
        <table>

            <thead><tr>
                { thead.map((header, index) => <th key={index}>{header.name}</th> )}
            </tr></thead>

            <tbody>
                { tbody[0].map((cell, rowIndex) =>

                    <tr key={rowIndex}>

                        {tbody.slice(0,thead.length).map((col, colIndex) =>
                        <td key={colIndex}>
                            {
                            thead[colIndex].type === "date" ?
                    
                            <DateTime value={col[rowIndex]+''} onChange={(value:string)=>setCellValue(rowIndex, colIndex, value)} />
                            
                            :
                            
                            <Field type={thead[colIndex].type}
                            bind={[col[rowIndex], (value:string)=>setCellValue(rowIndex, colIndex, value)]} />
                            }
                        </td>
                        )}

                        {rowIndex!==0 && rowIndex+1 === tbody[0].length ? 
                        <td onClick={removeRow}><BsFileMinusFill /></td> : null}
                    </tr>
                )}
                
            </tbody>


            {tbody[0].length === maxRows ? null :
            <tfoot>
                <tr>
                {
                    thead.map((th, index)=>
                        <td key={index}>{
                            index+1 !== thead.length ? null :
                            <BsFillPlusSquareFill onClick={addRow}/>
                        }
                        </td>
                    )
                }
                </tr>
            </tfoot>}

        </table>
    </>);
};