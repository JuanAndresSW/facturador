import React from "react";
/**
 * Un envoltorio flexible con alineación y justificación central.
 */
var FlexDiv = function (_a) {
    var children = _a.children;
    return (React.createElement("div", { style: { display: "flex", alignItems: "center", justifyContent: "center" } }, children));
};
export default FlexDiv;
