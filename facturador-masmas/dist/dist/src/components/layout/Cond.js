import React from "react";
/**Un envoltorio que sólo muestra los contenidos cuando la condición 'bool' es cumplida. */
var Cond = function (_a) {
    var bool = _a.bool, children = _a.children;
    return bool ? React.createElement(React.Fragment, null, children) : null;
};
export default Cond;
