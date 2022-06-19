import React, { ReactNode } from "react";

type props = {
  children: ReactNode,
  wrap?: boolean,
  justify?:string,
  align?:string
}

/**
 * Envoltorio flexible.
 * @param props.wrap - Si se debe distribuir los componentes hijos a multiples líneas. Por defecto es verdadero.
 * @param props.justify - El modo de ajustar en el eje X. Por defecto es center.
 * @param props.align - El modo de ajustar en el eje Y. Por defecto es center.
 */
const FlexDiv: React.FC<props> = ({children, wrap=true, justify="center", align="center"}) => {
    return (
      <div style={{ display: "flex", alignItems: align, justifyContent: justify, 
      flexFlow:wrap?'wrap':'nowrap', width:'100%' }}>
        {children}
      </div>
    )
}
export default FlexDiv;