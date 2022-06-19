import session from 'models/session';

/**Adapta el objeto de sesión recibido al formato esperado.*/
export default function adaptSession(json: string): session {
    const session = JSON.parse(json);
    return {
        accessToken:    session.accessToken,
        refreshToken:   session.refreshToken,
        username:       session.username,
        actives:        session.actives,
        passives:       session.passives,
        IDTrader:       session.IDTrader
    }
}