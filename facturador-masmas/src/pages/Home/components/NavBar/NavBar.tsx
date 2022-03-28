import React from "react";
import { NavLink } from "react-router-dom";
import "./NavBar.css";

type props = {
  tabs: {path:string, icon?: JSX.Element, label:string}[];
};

export default function NavBar({ tabs }: props) {

  return (
      <ul className="tab-set">

        {tabs.map((tab, index) => (

          <NavLink key={index} to={"."+tab.path}>
            {tab.icon}
            {tab.label}
          </NavLink>

        ))}

      </ul>

  );
}
