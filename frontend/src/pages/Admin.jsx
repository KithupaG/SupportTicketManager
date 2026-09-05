import {useAuth} from "../contexts/AuthContext.jsx";
import {useEffect, useState} from "react";
import {apiRequest} from "../api/client.js";

export default function Admin() {
    const { user, logoutUser } = useAuth()
    const [users, setUsers] = useState([])
    const [error, setError] = useState(null)

    useEffect(() => {
        apiRequest('/admin/users').then(setUsers).catch((err) => setError(err.message))
    }, [])

    return (
        <div>
            <header>
                <strong>Admin console</strong>
                <span>Signed in as {user?.email}</span>
            </header>
            <h1>All users</h1>
            {error && <p style={{color: 'red'}}>{error}</p>}
            {!error && users.length === 0 && <p>No users found.</p>}

            <ul>
                {users.map((u) => (
                    <li key={u.id}>
                        {u.id} — {u.firstName} {u.lastName} ({u.email}) — {u.role}
                    </li>
                ))}
            </ul>
            <button onClick={logoutUser}>Sign out</button>
        </div>
    )
}
