import React from "react";
import { NavLink } from "react-router-dom";
import Protected from "../../script/Protected";
import "./NavBar.css";

type props = {
  children: JSX.Element[];
};

export default function Main({ children }: props) {

  return (
    <Protected>
      <ul className="tab-set">
        {children.map((tab: JSX.Element) => (
          <NavLink key={tab.props.accessKey} to={tab.props.accessKey}>
            {tab.props.tabHeader}
          </NavLink>
        ))}
      </ul>
    </Protected>
  );
}
