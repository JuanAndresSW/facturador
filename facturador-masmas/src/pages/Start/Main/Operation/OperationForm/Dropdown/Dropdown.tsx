import React, { useState } from "react";
import { Link } from "react-router-dom";
import { BiCaretDown, BiCaretUp, BiPlusCircle } from "react-icons/bi";
import OutsideClickHandler from "react-outside-click-handler";
import './Dropdown.css';

type props = {
    label: string;
    content?: any[];
    link?: string;
    current: { id: number, name: string, address: string };
    setter: Function;
    allow1OrLess: boolean;
}

/**
 * Un input `<select>` personalizado.
 * @param label El título del input.
 * @param content Un array de arrays de objetos para armar las opciones.
 * @param current El objeto actualmente seleccionado.
 * @param link Enlace para agregar más opciones.
 * @param setter La función para cambiar el objeto seleccionado.
 * @param allow1OrLess Si el input debe mostrarse cuando hay menos de 2 opciones.
 */
export default function Dropdown({ label, content, link, current, setter, allow1OrLess }: props): JSX.Element {

    //Establece un controlador de estado para el dropdown.
    const [controller, setController] = useState(false);

    return (
        current === undefined ? <>Error: 0 puntos de venta recibidos</> :
        (content === undefined || current === null) ? <>Cargando...</> :
            (!allow1OrLess && content.length < 2 ) ? <div>{current.name+': '+current.address}</div> :

                <label> {label}
                    <OutsideClickHandler onOutsideClick={() => setController(false)}>
                        <div className="wrapper">
                            <div className="dropdown" onClick={() => setController(!controller)}>
                                <div>
                                    <h3>{current.name}</h3>
                                    <p>{current.address}</p>
                                </div>
                                {controller ? <BiCaretUp /> : <BiCaretDown />}
                            </div>

                            <div className={controller ? "drop-content" : 'drop-content disable'}>

                                { content.length > 10 ?
                                    <input type="search" placeholder="Buscar por dirección" /> : <></>
                                }

                                {content.map((option, index) => (
                                    <div
                                        key={index}
                                        onClick={() => {
                                            setter(option);
                                            setController(false);
                                        }}
                                        className={option.id === current.id ? "highlight" : ""}
                                    >
                                        <h3>{option.name}</h3>
                                        {option.address}
                                    </div>
                                ))}
                                <Link to={link}><BiPlusCircle /></Link>
                            </div>
                        </div>
                    </OutsideClickHandler>
                </label>
    )
}