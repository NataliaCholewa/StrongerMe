import { Routes, Route } from 'react-router-dom'
import UserList from './components/UserList'
import UserDetails from './components/UserDetails'
import AddUser from './components/AddUser'

function App() {
  return (
    <Routes>
      <Route path="/" element={<UserList />} />
      <Route path="/users/:id" element={<UserDetails />} />
      <Route path="/add-user" element={<AddUser />} />
    </Routes>
  )
}

export default App
