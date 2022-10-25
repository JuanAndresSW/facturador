import React from "react";
import { Link } from "react-router-dom";
import { BiPlus } from "react-icons/bi";

/**Un signo + con Link a la dirección especificada.*/
const PlusIcon: React.FC<{ link: string, title: string }> = ({ link, title }) => {
    return (
    <Link title={title} to={link} style={{ flex: .2, marginTop: ".3rem", fontSize: "1.2rem", display: "block", textAlign: "center", color: "#444" }}>
      <BiPlus />
    </Link>)
}
export default PlusIcon;

