import React, { ReactNode } from "react";

type props = {
  children: ReactNode,
  wrap?: boolean,
  justify?:string,


}

/**
 * Un envoltorio flexible con alineación y justificación central.
 */
const FlexDiv: React.FC<props> = ({children, wrap=true, justify="center"}) => {
    return (
      <div style={{ display: "flex", alignItems: "center", justifyContent: justify, 
      flexFlow:wrap?'wrap':'nowrap', width:'100%' }}>
        {children}
      </div>
    )
}
export default FlexDiv;