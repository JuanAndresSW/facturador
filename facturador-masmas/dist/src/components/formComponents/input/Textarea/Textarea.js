import React from "react";
import './Textarea.css';
export default function Textarea(_a) {
    var label = _a.label, _b = _a.maxLength, maxLength = _b === void 0 ? 50 : _b, bind = _a.bind;
    return (React.createElement(React.Fragment, null,
        React.createElement("label", null, label),
        React.createElement("textarea", { maxLength: maxLength, value: bind[0], onChange: function (e) { return bind[1](e.target.value); } })));
}
