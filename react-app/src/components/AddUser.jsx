import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

function AddUser() {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    gender: '',
    weight: '',
    height: ''
  })

  const navigate = useNavigate()

  const handleChange = e => {
    const { name, value } = e.target
    setFormData(prev => ({ ...prev, [name]: value }))
  }

  const handleSubmit = e => {
    e.preventDefault()

    fetch('http://localhost:8080/api/users', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + btoa('jan.kowalski@example.com:jan123')
      },
      body: JSON.stringify(formData)
    })
      .then(res => {
        if (res.ok) navigate('/')
        else throw new Error('Nie udało się dodać użytkownika')
      })
      .catch(err => alert(err.message))
  }

  return (
    <form onSubmit={handleSubmit}>
      <h1>Dodaj użytkownika</h1>
      <input name="email" placeholder="Email" onChange={handleChange} required />
      <input name="password" placeholder="Hasło" onChange={handleChange} required />
      <input name="firstName" placeholder="Imię" onChange={handleChange} />
      <input name="lastName" placeholder="Nazwisko" onChange={handleChange} />
      <input name="gender" placeholder="Płeć" onChange={handleChange} />
      <input name="weight" placeholder="Waga (kg)" onChange={handleChange} type="number" />
      <input name="height" placeholder="Wzrost (cm)" onChange={handleChange} type="number" />
      <button type="submit">Dodaj</button>
    </form>
  )
}

export default AddUser
