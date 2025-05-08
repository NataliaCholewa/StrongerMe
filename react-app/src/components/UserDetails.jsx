import { useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'

function UserDetails() {
  const { id } = useParams()
  const [user, setUser] = useState(null)

  useEffect(() => {
    fetch(`http://localhost:8080/api/users/${id}`, {
      headers: {
        Authorization: 'Basic ' + btoa('jan.kowalski@example.com:jan123')
      }
    })
      .then(res => res.json())
      .then(data => setUser(data))
      .catch(err => console.error(err))
  }, [id])

  if (!user) return <p>Ładowanie...</p>

  return (
    <div>
      <h1>{user.firstName} {user.lastName}</h1>
      <p>Email: {user.email}</p>
      <p>Płeć: {user.gender}</p>
      <p>Waga: {user.weight} kg</p>
      <p>Wzrost: {user.height} cm</p>
    </div>
  )
}

export default UserDetails
