import React, { useEffect, useState } from "react";
import { BiChevronDown, BiChevronUp, BiUserCircle } from 'react-icons/bi';
import Session from "utils/Session";
import fetchAvatar from 'services/account/fetchAvatar';
import OutsideClickHandler from 'react-outside-click-handler';
import './ProfileMenu.css';


export default function ProfileMenu(): JSX.Element {
    //Imágen de avatar de usuario.
    const [img, setImg] = useState(undefined);

    useEffect(()=>{
        if (Session.getAvatar() === '') fetchAvatar(handleResponse);
        else setImg(Session.getAvatar());
    }, []);

    function handleResponse(state: number, data: string) {
        if (state === 200) {
            Session.setAvatar(data);
            setImg(URL.createObjectURL(new Blob([data])));
        }
    }

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
                    <li>{Session.getUsername() + ': '}</li>
                    <li>Configuración</li>
                    <li onMouseDown={() => logOut()}>Cerrar sesión</li>
                </ul>
            </div>
        </OutsideClickHandler>
    )
}