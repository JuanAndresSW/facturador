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
  pointOfSale: {
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
}
