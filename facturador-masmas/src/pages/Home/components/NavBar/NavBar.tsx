import React from "react";
import { NavLink } from "react-router-dom";
import "./NavBar.css";

type props = {
  tabs: {path:string, icon?: JSX.Element, label:string}[];
};

/**Una lista de <NavLink>. Toma como parámetro una lista (array) de links (tabs).
 * @param props.tabs[].path - La dirección a la cual navegar, relativamente a la dirección actual.
 * @param props.tabs[].icon - Icono opcional a ser agregado al label.
 * @param props.tabs[].label - Texto del elemento de navegación.
*/
export default function NavBar({ tabs }: props) {

  return (
      <nav>

        {tabs.map((tab, index) => (

          <NavLink key={index} to={"."+tab.path}>
            {tab.icon}
            {tab.label}
          </NavLink>

        ))}

      </nav>

  );
}
