import { useAuth } from "../contexts/AuthContext.jsx";
import { useState, useEffect } from "react";
import {Link} from "react-router-dom";
import {apiRequest} from "../api/client.js";

export default function Tickets() {
    const { user, logoutUser } = useAuth()
    const [tickets, setTickets] = useState([])
    const [error, setError] = useState(null)

    useEffect(() => {
        apiRequest('/tickets')
            .then(setTickets)
            .catch((err) => setError(err.message))
    }, [])

    return (
        <div>
            <header>
                <strong>Support Portal</strong>
                <span>Signed in as {user?.email}</span>
                <button onClick={logoutUser}>Sign out</button>
            </header>

            <h1>Your tickets</h1>
            <Link to="/tickets/new">New Ticket</Link>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {!error && tickets.length === 0 && <p>No tickets yet.</p>}

            <ul>
                {tickets.map((t) => (
                    <li key={t.id}>
                        <Link to={`/tickets/${t.id}`}>
                            {t.title} - {t.priority} - {t.status}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    )
}