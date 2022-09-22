import session from 'models/session';

/**Adapta el objeto de sesión recibido al formato esperado.*/
export default function jsonToSession(json: string): session {
    const session = JSON.parse(json);
    return {
        accessToken:    session.accessToken,
        refreshToken:   session.refreshToken,
        username:       session.username,
        IDTrader:       session.IDTrader
    }
}