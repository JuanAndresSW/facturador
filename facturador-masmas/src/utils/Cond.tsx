import React, { ReactNode } from "react";
const Cond: React.FC<{ bool: boolean, children: ReactNode }> = ({ bool, children }) => {
    return bool ? <>{children}</> : null
}
export default Cond;