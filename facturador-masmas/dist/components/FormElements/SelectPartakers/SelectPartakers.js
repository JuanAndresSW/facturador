import React, { useState } from "react";
import DocData from '../../../script/DocData';
import './SelectPartakers.css';
//return a selection list for points of sale, third party partakers and groups
export default function SelectPartakers() {
    DocData.fetchFormData();
    //define index of default point of sale, third party and group
    DocData.initializeValues();
    var _a = useState(0), pointOfSale = _a[0], setPointOfSale = _a[1];
    var _b = useState(0), thirdParty = _b[0], setThirdParty = _b[1];
    var _c = useState(-1), group = _c[0], setGroup = _c[1]; //default is undefined
    //sets the search term as the item value if exists
    function search(searchTerm, item) {
        var index = DocData.getIndexIfExists(searchTerm, item);
        if (index !== -1) {
            switch (item) {
                case "third-parties":
                    setThirdParty(index);
                    break;
                case "groups":
                    setGroup(index);
                    break;
            }
        }
    }
    ;
    //updates values to send on input value change event
    function handlePointChange(e) {
        DocData.setPointOfSale(parseInt(e.target.value));
        setPointOfSale(parseInt(e.target.value));
        return;
    }
    ;
    function handlePartyChange(e) {
        DocData.setThirdParty(parseInt(e.target.value));
        setThirdParty(parseInt(e.target.value));
        return;
    }
    ;
    function handleGroupChange(e) {
        DocData.setGroup(parseInt(e.target.value));
        setGroup(parseInt(e.target.value));
        return;
    }
    ;
    return (React.createElement(React.Fragment, null,
        React.createElement("div", { className: "form-section" },
            React.createElement("label", { htmlFor: "select-point" }, "Punto de venta"),
            React.createElement("select", { className: "input-control", value: pointOfSale, id: "selec-point", required: true, onChange: function (e) { return handlePointChange(e); } }, DocData.getPointsOfSale().map(function (point, index) { return (React.createElement("option", { value: index, key: index, title: point.name }, point.address)); }))),
        React.createElement("div", { className: "form-section" },
            React.createElement("label", { htmlFor: "select-third-party" }, "Terceros"),
            React.createElement("select", { className: "input-control", value: thirdParty, id: "select-third-party", required: true, onChange: function (e) { return handlePartyChange(e); } }, DocData.getThirdParties().map(function (party, index) { return (React.createElement("option", { value: index, key: index, title: party.vatType }, party.name)); })),
            React.createElement("input", { className: "search-box", type: "text", onChange: function (e) { return search(e.target.value, "third-parties"); } }),
            React.createElement("label", { htmlFor: "select-group" }, "Grupos"),
            React.createElement("select", { className: "input-control", value: group, id: "select-group", required: true, onChange: function (e) { return handleGroupChange(e); } },
                React.createElement("option", { value: -1, disabled: true }),
                DocData.getGroups().map(function (party, index) { return (React.createElement("option", { value: index, key: index, title: party.members.toString() }, party.name)); })),
            React.createElement("input", { className: "search-box", type: "search", onChange: function (e) { return search(e.target.value, "groups"); } }))));
}
