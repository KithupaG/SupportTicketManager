const TOKEN_KEY = 'ems_token'
const USER_KEY = 'ems_user'

export function getStoredUser() {
    const raw = localStorage.getItem(USER_KEY)
    if(!raw) return null
    try {
        return JSON.parse(raw)
    }catch {
        return null
    }
}

export function setStoredUser(user) {
    localStorage.setItem(USER_KEY, JSON.stringify(user))
}

export function clearStoredUser() {
    localStorage.removeItem(USER_KEY)
}

export function logout() {
    clearToken()
    clearStoredUser()
}

export function getToken() {
    return localStorage.getItem(TOKEN_KEY)
}

export function setToken(token) {
    localStorage.setItem(TOKEN_KEY, token)
}

export function clearToken() {
    localStorage.removeItem(TOKEN_KEY)
}

export async function apiRequest(path, { method = 'GET', body } = {}) {
    const headers = {}

    if(body !== undefined) {
        headers['Content-Type'] = 'application/json'
    }

    const token = getToken()
    if(token) {
        headers['Authorization'] = `Bearer ${token}`
    }

    const response = await fetch(`/api${path}`, {
        method,
        headers,
        body: body !== undefined ? JSON.stringify(body) : undefined,
    })

    const text = await response.text()
    const data = text ? JSON.parse(text) : null

    if(!response.ok) {
        throw new Error(data?.message || `Request failed reason: ${response.status}`)
    }

    return data
}