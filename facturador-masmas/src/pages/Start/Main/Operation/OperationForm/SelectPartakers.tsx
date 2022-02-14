import React, { useState } from "react";
import { BiChevronsDown, BiChevronsUp, BiGroup, BiUser } from "react-icons/bi";
import Dropdown from "./Dropdown/Dropdown";

type props = {
  setter: any;
  dataToShow: {
    partakers: { pointsOfSale: any[], partners: any[], groups: any[] };
    currentPoint: { id: number; address: string; name: string };
    currentPartner: { id: number; address: string; name: string };
    currentGroup: { id: number; }
  };
  flux: string;
};

/**Devuelve una lista de selección de puntos de venta, socios y grupos.
 * @param setter La función para cambiar los valores de los input que se enviarán al servidor.
 * @param dataToShow Los datos a partir de cuales se construyen los input.
 * @param flux El flujo de emisión del documento.
*/
export default function SelectPartakers({ setter, dataToShow, flux }: props): JSX.Element {
  //controlador para el tipo de tercero
  const [thirdType, setThirdType] = useState('partner');

  return (
    <>

      <div className="form-section">
        <Dropdown
          label={"Punto de venta"}
          content={dataToShow.partakers?.pointsOfSale}
          link={"/puntos-de-venta"}
          current={dataToShow.currentPoint}
          setter={setter.setPointOfSale}
          allow1OrLess={false}
        />
      

      {
        flux === 'in' ? <BiChevronsUp /> : <BiChevronsDown />
      }

      {
        dataToShow?.partakers?.groups === undefined ? <></> : <div className='custom-radio'><BiUser /><BiGroup /></div>
      }

      
        <Dropdown
          label={"Terceros"}
          content={dataToShow.partakers?.partners}
          link={"/puntos-de-venta"}
          current={dataToShow.currentPartner}
          setter={setter.setPartner}
          allow1OrLess={true}
        />
      </div>

    </>
  );
}
