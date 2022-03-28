import React, { ReactNode } from "react";

/**
 * Un envoltorio flexible con alineación y justificación central.
 */
const FlexDiv: React.FC<{ children: ReactNode }> = ({children}) => {
    return (
      <div style={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
        {children}
      </div>
    )
}
export default FlexDiv;