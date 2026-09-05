import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider } from './contexts/AuthContext.jsx'
import { RequireAuth, RequireRole } from './components/guards'
import Login from './pages/Login'
import Register from './pages/Register'
import Home from './pages/Home'
import Admin from './pages/Admin'
import Tickets from "./pages/Tickets.jsx";
import NewTicket from "./pages/NewTicket.jsx";
import TicketDetail from "./pages/TicketDetail.jsx";

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
                    <Route path="/tickets" element={
                        <RequireAuth>
                            <Tickets />
                        </RequireAuth>
                    }/>
                    <Route path="/tickets/new" element={
                        <RequireAuth>
                            <NewTicket />
                        </RequireAuth>
                    }/>
                    <Route path="/tickets/:id" element={
                        <RequireAuth>
                            <TicketDetail />
                        </RequireAuth>
                    }/>
                    <Route path="*" element={<Navigate to="/" replace/>}/>
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    )
}
