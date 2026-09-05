import {Navigate} from "react-router-dom";
import { useAuth } from "../contexts/AuthContext.jsx";

export function RequireAuth({ children }) {
    const { isAuthenticated, loading } = useAuth()
    if (loading) return <p>Loading...</p>
    if(!isAuthenticated) return <Navigate to="/login" replace />
    return children
}

export function RequireRole({role, children}) {
    const { user } = useAuth()
    if(user?.role !== role) return <Navigate to="/" replace />

    return children
}