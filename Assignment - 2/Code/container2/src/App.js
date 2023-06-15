import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginForm from './login';
import Profile from "./profile";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path = '/' element={<LoginForm />}></Route>
        <Route path = '/profile' element={<Profile />} ></Route>
      </Routes>
      </BrowserRouter>
     
    </div>
  );
}

export default App;