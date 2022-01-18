import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import './TabbedPane.css';
export default function TabbedPane(_a) {
    var children = _a.children;
    var router = useRouter();
    var _b = useState(children[0].props.label), _c = _b[0], currentTab = _c.currentTab, url = _c.url, setCurrentTab = _b[1];
    var handleTabSwitch = function (newActiveTab, url) {
        setCurrentTab({ newActiveTab: newActiveTab, url: url });
    };
    //sincronizar la dirección url
    useEffect(function () {
        router.push(url, undefined, { shallow: true });
    }, [currentTab]);
    return (React.createElement("div", { className: 'tab-set' },
        React.createElement("ul", null, children.map(function (tab) { return (React.createElement("li", { className: tab.props.label === currentTab ? 'current-tab' : '', key: tab.props.label, onMouseDown: function () { handleTabSwitch(tab.props.label, tab.props.accessKey); } }, tab.props.label)); })),
        children.map(function (tab) {
            if (tab.props.label === currentTab)
                return React.createElement("div", { className: 'tab-content', key: tab.props.label }, tab.props.children);
        })));
}
