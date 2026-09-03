import { createContext, useContext, useEffect, useState } from "react";
import { apiRequest, getToken, getStoredUser, logout, setStoredUser, setToken } from "../api/client.js";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
    const [user, setUser] = useState(() => getStoredUser())
    const [loading, setLoading] = useState(() => Boolean(getToken()))

    useEffect(() => {
        if (!getToken()) return
        apiRequest('/auth/me').then(setUser).finally(() => setLoading(false))
    }, []);

    async function login(email, password) {
        const data = await apiRequest('/auth/login', {method: 'POST', body: {email, password} })
        setToken(data.token)
        setStoredUser(data.user)
        setUser(data.user)
        return data.user
    }

    async function register(payload) {
        const user = await apiRequest('/auth/register', {method: 'POST', body: payload})
        const data = await apiRequest('/auth/login', {
            method: 'POST',
            body: {
                email: payload.email,
                password: payload.password
            },
        })
        setToken(data.token)
        setStoredUser(data.user)
        setUser(data.user)
        return data.user
    }

    function logoutUser() {
        logout()
        setUser(null)
    }

    const value = {
        user,
        setUser,
        loading,
        login,
        register,
        logoutUser,
        isAuthenticated: Boolean(user),
        role: user?.role ?? null,
    }

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
    const ctx = useContext(AuthContext)
    if(!ctx) throw new Error('useAuth must be used within an AuthProvider')
    return ctx
}