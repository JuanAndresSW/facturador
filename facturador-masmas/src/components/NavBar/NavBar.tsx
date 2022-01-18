import React from "react";
import { NavLink } from "react-router-dom";
import Protected from "../../script/Protected";
import './NavBar.css'

type props = {
    children: JSX.Element[],
}

export default function Main({children}:props){

    /* const [activeTab, setActiveTab] = useState(url);
    function handleTabSwitch(url:string):void {
        window.history.pushState(url,"",url);
        setActiveTab(url);
    } */

    return (
    <Protected>
        <ul className="tab-set">
            {
            children.map((tab:JSX.Element) => (
            <NavLink key={tab.props.accessKey}
            to={tab.props.accessKey}>{tab.props.tabHeader}</NavLink>
            ))
            }
        </ul>

        {/* children.map(tab => {
            if (tab.props.accessKey === activeTab)
            return <div className={'tab-content'} key={tab.props.accessKey}>{tab.props.children}</div>
        }) */}
    </Protected>
    )
}