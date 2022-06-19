/**Objeto con los datos que constituyen una sesión de usuario. */
type session = {
    accessToken:    string;  //El JWT usado para autenticar peticiones.
    refreshToken:   string;  //El JWT usado para autenticar una solicitud de renovación de token de acceso.
    username:       string;  //El nombre del usuario.
    IDTrader:      string;   //El identificador del comerciante.
    actives:        string;  //La cantidad numérica de patrimonio activo del comerciante.
    passives:       string;  //La cantidad numérica de patrimonio pasivo del comerciante.
};
export default session;