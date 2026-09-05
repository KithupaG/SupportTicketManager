import {useAuth} from "../contexts/AuthContext.jsx";
import {Link} from "react-router-dom";

export default function Home() {
    const { user, logoutUser } = useAuth()

    return (
        <div>
            <header>
                <strong>Support Portal</strong>
                <span>Signed in as {user?.email}</span>
                <Link to="/tickets">My tickets</Link>
                <span>Signed in as {user?.email}</span>
                <button onClick={logoutUser}>Sign out</button>
            </header>
            <h1>Welcome, {user?.firstName}</h1>
            <p>Your dashboard will appear here soon</p>
        </div>
    )
}