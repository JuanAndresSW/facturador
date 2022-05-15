import React, { useEffect, useState } from "react";
import './OptionWithPhoto.css'
import defaultPhoto from 'assets/svg/default-photo.svg'
import { base64ToBlob } from "utilities/conversions";

type props = {
    title: string,
    subtitle: string,
    image: string,
    onClick?: Function
}

/**
 * Un link con un título, un subtitulo, una imágen y un icono de opción que ejecuta una función.
 * @param props.title El título del link.
 * @param props.subtitle El subtítulo.
 * @param props.image La imágen de fondo, en base 64.
 * @param props.onClick
 */
export default function OptionWithPhoto({ title, subtitle, image, onClick }: props): JSX.Element {

    function followMainLink(e: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void {
        e.preventDefault();
        onClick();
    } 

    //Convertir la imágen base 64 a URL.
    const [photo, setPhoto] = useState(undefined);
    useEffect(()=>{
        console.log('useEffect');
        if (image) {
            console.log('image is true');
            const photo = base64ToBlob(image);

            photo.then((blob)=>{
                setPhoto(URL.createObjectURL(blob));
                console.log('url: '+URL.createObjectURL(blob));
            }).catch(err => {
                console.log(err);
            })

            
        } 
    }, [])

    return (
        
        <div data-component='OptionWithPhoto'>

            <div>
            <a onClick={e=> followMainLink(e)}>
                <img src={photo? photo : defaultPhoto  } />
                <p><abbr title={title}>{title}</abbr></p>
                <span><p><abbr title={subtitle}>{subtitle}</abbr></p></span>
            </a>
            </div>   
        </div>
    )
}