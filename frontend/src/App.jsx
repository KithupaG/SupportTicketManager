import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider } from './contexts/AuthContext.jsx'
import { RequireAuth, RequireRole } from './components/guards'
import Login from './pages/Login'
import Register from './pages/Register'
import Home from './pages/Home'
import Admin from './pages/Admin'

export default function App() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <Routes>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                    <Route path="/" element={
                        <RequireAuth>
                            <Home/>
                        </RequireAuth>
                    }/>
                    <Route path="/admin" element={
                        <RequireAuth>
                            <RequireRole role="ADMIN">
                                <Admin/>
                            </RequireRole>
                        </RequireAuth>
                    }/>
                    <Route path="*" element={<Navigate to="/" replace/>}/>
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    )
}
