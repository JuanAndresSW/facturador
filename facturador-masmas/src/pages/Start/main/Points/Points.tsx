import React, { useState } from "react";
export default function Spots() {
  const [outlet, setOutlet] = useState(<></>);
  //si outlet es un fragmento, las opciones iniciales estan activas
  const initial = outlet.type === (<></>).type;

  return (
    <>
      <p>Punto 1</p>
      <p>+nuevo punto</p>
    </>
  );
}
