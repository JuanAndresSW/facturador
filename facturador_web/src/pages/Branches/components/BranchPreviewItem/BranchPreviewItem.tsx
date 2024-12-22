import React from "react";
import './BranchPreviewItem.css';
import defaultPhoto from 'assets/svg/default-photo.svg';

type props = {
    title: string,
    subtitle: string,
    image: Blob,
    onClick?: Function
}

/**
 * Un cuadro con una imágen, un título y subtitulo en la parte inferior.
 * @param props.title      - El título del cuadro.
 * @param props.subtitle   - El subtítulo.
 * @param props.image      - La imágen de fondo, en base 64.
 * @param props.onClick    - Función de evento de clic.
 */
export default function BranchPreviewItem({ title, subtitle, image, onClick }: props): JSX.Element {

    return (
        
        <div data-component='branch-preview-item'>

            <div>
            <a onClick={()=>onClick()}>
                <img src={image.size>10?URL.createObjectURL(image):defaultPhoto} />
                <p><abbr title={title}>{title}</abbr></p>
                <span><p><abbr title={subtitle}>{subtitle}</abbr></p></span>
            </a>
            </div>   
        </div>
    )
}