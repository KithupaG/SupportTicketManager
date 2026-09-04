import {useNavigate} from "react-router-dom";
import {useState} from "react"
import { useAuth } from "../contexts/AuthContext.js";

export default function Login() {
    const { login } = useAuth()
    const navigate = useNavigate()
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState(null)

    async function handleSubmit(e) {
        e.preventDefault()
        setError(null)

        try {
            const user = await login(email, password)
            navigate(user.role === 'ADMIN' ? '/admin' : '/')
        }catch (err) {
            setError(err.message)
        }
    }

    return (
        <div>
            <h1>Sign in</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    Email
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
                </label>
                <label>
                    Password
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
                </label>
                {error && <p style={{color: 'red'}}>{error}</p>}
                <button type="submit">Sign in</button>
            </form>
            <p>
                No Account? <a href="/register">Register Here</a>
            </p>
        </div>
    )
}