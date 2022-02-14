/**Una cuenta de usuario nueva a ser creada por el servidor. */
export interface account {
  user: {
    username: string;
    email: string;
    password: string;
    avatar: File;
  };
  trader: {
    businessName: string;
    vatCategory: string;
    code: string;
    grossIncome: string;
  };
}

/**Un punto de venta nuevo a ser creado por el servidor. */
export interface pointOfSale {
  name: string;
  address: string;
  locality: string;
  postalCode: string;
  email: string;
  phone: string;
  website: string;
  color: string;
  logo: File;
};

/**Un documento comercial nuevo a ser creado por el servidor. */
export interface document {
  documentType: string,
  pointOfSale: number,
  partner: number,
  group: number
}

/**Una sesión de usuario guardada localmente, excluyendo el avatar de usuario. */
export interface session {
  token: string,
  name: string,
  active: string,
  passive: string
}