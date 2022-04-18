import React from "react";
import './OptionWithPhoto.css'
import defaultPhoto from 'assets/svg/default-photo.svg'

type props = {
    title: string;
    subtitle: string;
    image: File;
    option?: {icon:JSX.Element, onClick: Function};
    onClick?: Function;
    ID: number;
}

/**
 * Un link con un título, un subtitulo, una imágen y un icono de opción que ejecuta una función.
 * @param props.title El título del link.
 * @param props.subtitle El subtítulo.
 * @param props.image La imágen de fondo.
 * @param props.option Un objeto {icon, onClick}.
 * @param props.onClick
 * @param props.ID Un número identificador que se argumenta en todas las llamadas a funciones dentro del componente.
 */
export default function OptionWithPhoto({ title, subtitle, image, option, onClick, ID }: props): JSX.Element {

    function followMainLink(e: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void {
        e.preventDefault();
        onClick(ID);
    } 
    
    function followSecondaryLink(): void {
        option.onClick(ID)
    }

    return (
        <div data-component='OptionWithPhoto'>

            <div>
            <a onClick={e=> followMainLink(e)}>
                <img src={image? URL.createObjectURL(image) : defaultPhoto  } />
                <p><abbr title={title}>{title}</abbr></p>
                <span>{subtitle}</span>
            </a>
            </div>
            
            {
            !option? null:
            <menu onClick={followSecondaryLink}>
                {option.icon}
            </menu>
            }    
        </div>
    )
}