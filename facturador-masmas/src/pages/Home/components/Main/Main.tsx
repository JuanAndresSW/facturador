import React from "react";
import './Main.css';

/**Un menú de navegación a la izquierda, con el contenido a la derecha. */
export default function Main({menu, content}: {menu: JSX.Element, content: JSX.Element}): JSX.Element {
    return (
        <main>
            <aside style={{position:'relative'}}>{menu}</aside>
            <article>{content}</article>
        </main>
    );
}