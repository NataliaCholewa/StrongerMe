import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

function UserList() {
  const [users, setUsers] = useState([])
  const [loading, setLoading] = useState(false)

  const fetchUsers = () => {
    setLoading(true)
    fetch('http://localhost:8080/api/users', {
      headers: {
        Authorization: 'Basic ' + btoa('jan.kowalski@example.com:jan123')
      }
    })
      .then(res => res.json())
      .then(data => setUsers(data))
      .catch(err => console.error(err))
      .finally(() => setLoading(false))
  }

  useEffect(() => {
    fetchUsers()
  }, [])

  return (
    <div>
      <h1>Lista użytkowników</h1>
      <button onClick={fetchUsers} disabled={loading}>
        {loading ? 'Odświeżam...' : 'Odśwież'}
      </button>
      <ul>
        {users.map(user => (
          <li key={user.id}>
            <Link to={`/users/${user.id}`}>
              {user.firstName} {user.lastName}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default UserList
