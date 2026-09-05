import {useAuth} from "../contexts/AuthContext.jsx";

export default function Home() {
    const { user, logoutUser } = useAuth()

    return (
        <div>
            <header>
                <strong>Support Portal</strong>
                <span>Signed in as {user?.email}</span>
            </header>
            <h1>Welcome, {user?.firstName}</h1>
            <p>Your dashboard will appear here soon</p>
            <button onClick={logoutUser}>Sign out</button>
        </div>
    )
}