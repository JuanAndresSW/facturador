import React from "react";
import './Main.css';

/**Un contenedor dividido en dos, con un menú de navegación a la izquierda, 
 * y con el contenido a la derecha. */
export default function Main({menu, content}: {menu: JSX.Element, content: JSX.Element}): JSX.Element {
    return (
        <main>
            <aside style={{position:'relative'}}>{menu}</aside>
            <article>{content}</article>
        </main>
    );
}