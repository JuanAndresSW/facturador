import React from 'react';
import { useState } from 'react';
import './TabbedPane.css';
export default function TabbedPane(_a) {
    var children = _a.children;
    var _b = useState(children[0].props.label), currentTab = _b[0], setCurrentTab = _b[1];
    var handleTabSwitch = function (newActiveTab) {
        setCurrentTab(newActiveTab);
    };
    return (React.createElement("div", { className: 'tab-set' },
        React.createElement("ul", null, children.map(function (tab) { return (React.createElement("li", { className: tab.props.label === currentTab ? 'current-tab' : '', key: tab.props.label, onMouseDown: function () { handleTabSwitch(tab.props.label); } }, tab.props.label)); })),
        children.map(function (tab) {
            if (tab.props.label === currentTab)
                return React.createElement("div", { className: 'tab-content', key: tab.props.label }, tab.props.children);
        })));
}
