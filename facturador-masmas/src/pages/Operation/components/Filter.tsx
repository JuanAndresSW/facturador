import React from 'react';
import { Cond } from 'components/wrappers';
import operationFilters from "../utilities/operationFilters";
import {documentClassCode} from '../models/operation';
import documentProp from '../models/documentProp';

/**Muestra el contenido sólo si la propiedad 'by' es incluida en la operación actual. */
export default function Filter({by, classCode, children}:{ by: documentProp, classCode: documentClassCode,children:  React.ReactNode }) {
    return <Cond bool={operationFilters[by].includes(classCode)}>{children}</Cond>
}