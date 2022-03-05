import React, { useState } from "react";
export default function Spots() {
    var _a = useState(React.createElement(React.Fragment, null)), outlet = _a[0], setOutlet = _a[1];
    //si outlet es un fragmento, las opciones iniciales estan activas
    var initial = outlet.type === (React.createElement(React.Fragment, null)).type;
    return (React.createElement(React.Fragment, null,
        React.createElement("p", null, "Punto 1"),
        React.createElement("p", null, "+nuevo punto")));
}
