import {useAuth} from "../contexts/AuthContext.js";
import {useNavigate} from "react-router-dom";
import {useState} from "react";

export default function Register() {
    const { register } = useAuth()
    const navigate = useNavigate()
    const [form, setForm] = useState({
        email: '',
        password: '',
        firstName: '',
        lastName: '',
        role: 'CUSTOMER',
        phone: '',
        address: '',
        department: '',
        maxActiveTickets: 10
    })

    const [error, setError] = useState(null)

    function updateField(e) {
        const {name, value} = e.target
        setForm((prev) => ({...prev, [name]: value}))
    }

    async function handleSubmit(e) {
        e.preventDefault()
        setError(null)
        try {
            const user = await register(form)
            navigate(user.role === 'ADMIN' ? '/admin' : "/")
        }catch (err) {
            setError(err.message)
        }
    }

    return (
        <div>
            <h1>Create account</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    Role
                    <select name="role" value={form.role} onChange={updateField}>
                        <option value="CUSTOMER">Customer</option>
                        <option value="ADMIN">Admin</option>
                        <option value="SUPPORTER_AGENT">Support Agent</option>
                    </select>
                </label>
                <label>
                    Email
                    <input name="email" type="email" value={form.email} onChange={updateField} required/>
                </label>
                <label>
                    Password
                    <input name="password" type="password" value={form.password} onChange={updateField} minLength={8} required/>
                </label>
                <label>
                    First Name
                    <input name="firstName" type="firstName" value={form.firstName} onChange={updateField} required/>
                </label>
                <label>
                    Last Name
                    <input name="lastName" type="lastName" value={form.lastName} onChange={updateField} required/>
                </label>

                {form.role === 'CUSTOMER' && (
                    <>
                        <label>
                            Phone
                            <input name="phone" value={form.phone} onChange={updateField}/>
                        </label>
                        <label>
                            Address
                            <input name="address" value={form.address} onChange={updateField}/>
                        </label>
                    </>
                )}

                {(form.role === 'ADMIN' || form.role === 'SUPPORTER_AGENT') && (
                    <>
                        <label>
                            Department
                            <input name="department" value={form.department} onChange={updateField}/>
                        </label>
                        {form.role === 'SUPPORTER_AGENT' && (
                            <label>
                                Max active tickets
                                <input name="maxActiveTickets" type="number" value={form.maxActiveTickets} onChange={updateField}/>
                            </label>
                        )}
                    </>
                )}

                {error && <p style={{ color: 'red' }}>{error}</p>}
                <button type="submit">Create account</button>
            </form>
            <p>
                Have an account? <a href="/login">Sign in</a>
            </p>
        </div>
    )
}