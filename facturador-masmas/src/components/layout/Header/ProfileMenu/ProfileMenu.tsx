import React, { useEffect, useState } from "react";
import { BiChevronDown, BiChevronUp, BiUserCircle } from 'react-icons/bi';
import Session from "services/Session";
import UserAvatar from 'services/UserAvatar';
import OutsideClickHandler from 'react-outside-click-handler';
import './ProfileMenu.css';


export default function ProfileMenu(): JSX.Element {
    //Imágen de avatar de usuario.
    const [img, setImg] = useState(undefined);

    //Pedir la imágen en el primer renderizado.
    useEffect(() => {
        UserAvatar.getAvatar((HTTPState: number, URLObject: string) => {
            if (HTTPState === 200) setImg(URLObject);
        });
    }, []);

    //Navegación al cerrar sesión.
    function logOut(): void {
        Session.close();
        window.location.reload();
    }

    //Controlador de estado del menú.
    const [active, setActive] = useState(false);

    return (
        <OutsideClickHandler onOutsideClick={() => setActive(false)}>
            <div id="profile-menu" onMouseDown={() => setActive(!active)}>

                { (img === undefined)? <BiUserCircle /> :
                    <img src={img}></img>
                }

                {active ? <BiChevronUp /> : <BiChevronDown />}
            </div>

            <div id="profile-menu-list" className={active ? 'active' : ''}>
                <ul>
                    <li>{"Session.getUsername()" + ': '}</li>
                    <li>Configuración</li>
                    <li onMouseDown={() => logOut()}>Cerrar sesión</li>
                </ul>
            </div>
        </OutsideClickHandler>
    )
}