import {Link} from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { apiRequest } from "../api/client.js";
import {useState} from "react";

export default function NewTicket() {

    const navigate = useNavigate()
    const [form ,setForm] = useState({
        title: '',
        description: '',
        priority: 'LOW'
    })
    const [error, setError] = useState(null)

    function updateField(e) {
        const { name, value } = e.target
        setForm((prev) => ({...prev, [name]: value}))
    }

    async function handleSubmit(e) {
        e.preventDefault()
        setError(null)

        try {
            await apiRequest('/tickets', {
                method : 'POST',
                body: form
            })
            navigate('/tickets')
        }catch(err) {
            setError(err.message)
        }
    }

    return (
        <div>
            <h1>New Ticket</h1>
            <Link to="/tickets">Back to tickets</Link>
            <form onSubmit={handleSubmit}>
                <label>
                    Title
                    <input name="title" value={form.title} onChange={updateField} required />
                </label>
                <label>
                    Description
                    <textarea name="description" value={form.description} onChange={updateField} required />
                </label>
                <label>
                    Priority
                    <select name="priority" value={form.priority} onChange={updateField}>
                        <option value="LOW">Low</option>
                        <option value="MEDIUM">Medium</option>
                        <option value="HIGH">High</option>
                        <option value="URGENT">Urgent</option>
                    </select>
                </label>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <button type="submit">Create ticket</button>
            </form>
        </div>
    )
}