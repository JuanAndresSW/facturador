import React, { useState } from "react";
import { BiChevronDown, BiChevronUp } from "react-icons/bi";
import './Retractable.css';
export default function Retractable(_a) {
    var children = _a.children, label = _a.label, _b = _a.initial, initial = _b === void 0 ? true : _b;
    var _c = useState(initial), extended = _c[0], setExtended = _c[1];
    return (React.createElement("div", { className: extended ? "retractable" : "retractable folded" },
        React.createElement("div", { onClick: function () { return setExtended(!extended); } },
            label,
            extended ? React.createElement(BiChevronUp, null) : React.createElement(BiChevronDown, null)),
        React.createElement("div", null, children)));
}
