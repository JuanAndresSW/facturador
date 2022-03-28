import session from 'models/session';

export default function adaptSession(json: string): session {
    const session = JSON.parse(json);
    return {
        accessToken: session.accessToken,
        refreshToken: session.refreshToken,
        username: session.username,
        role: session.role,
        actives: session.active,
        passives: session.pasive
    }
}