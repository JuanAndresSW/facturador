import "react";
declare module 'react' {
  export interface HTMLAttributes<T> {
    tabHeader?: ReactNode;
  }
  export interface JsxAttributes<T> {
    flux?: 'in' | 'out';
  }
}
