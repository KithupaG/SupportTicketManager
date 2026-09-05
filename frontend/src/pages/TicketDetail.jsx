import {Link, useParams} from "react-router-dom";
import {useAuth} from "../contexts/AuthContext.jsx";
import {useEffect, useState} from "react";
import {apiRequest} from "../api/client.js";

export default function TicketDetail() {
    const { id } = useParams()
    const { user } = useAuth()
    const [ticket, setTicket] = useState(null)
    const [comments, setComments] = useState([])
    const [content, setContent] = useState('')
    const [error, setError] = useState(null)

    useEffect(() => {
        apiRequest(`/tickets/${id}`)
            .then(setTicket)
            .catch((err) => setError(err.message))
        apiRequest(`/comments/${id}`)
            .then(setComments)
            .catch((err) => setError(err.message))
    }, [id])

    async function addComment(e) {
        e.preventDefault()
        setError(null)
        try {
            const created = await apiRequest(`/comments/${id}/${user.id}/comment`, {
                method: 'POST',
                body: { content }
            })
            setComments((prev) => [...prev, created])
            setContent('')
        }catch(err) {
            setError(err.message)
        }
    }

    return (
        <div>
            <Link to="/tickets">Back to tickets</Link>
            <h1>Ticket #{id}</h1>
            {error && <p style={{ color: 'red'}}>{error}</p>}
            {ticket && (
                <p>
                    {ticket.title} - {ticket.priority} - {ticket.status}
                </p>
            )}
            <h3>Comments</h3>
            <ul>
                {comments.map((c) => (
                    <li key={c.id}>
                        <strong>{c.authorName}</strong>: {c.content}
                    </li>
                ))}
            </ul>
            <form onSubmit={addComment}>
                <textarea value={content} onChange={(e) => setContent(e.target.value)} placeholder="Write a comment..." required />
                <button type="submit">Add comment</button>
            </form>
        </div>
    )
}