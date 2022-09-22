import React from 'react';
import { Cond } from 'components/wrappers';
import operationFilters, {operationProp} from "../utilities/operationFilters";
import {operationCode} from '../models/operation';

/**Muestra el contenido sólo si la propiedad 'by' es incluida en la operación actual. */
export default function Filter({by, type, children}:{ by: operationProp, type: operationCode,children:  React.ReactNode }) {
    return <Cond bool={operationFilters[by].includes(type)}>{children}</Cond>
}