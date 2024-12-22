import React, { ReactNode } from "react";

/**Un envoltorio que sólo muestra los contenidos cuando la condición 'bool' es cumplida.
 * @param props.bool - La condición para mostrar el contenido.
*/
const Cond: React.FC<{ bool: boolean, children: ReactNode }> = ({ bool, children }) => {
    return bool ? <>{children}</> : null
}
export default Cond;