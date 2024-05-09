import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Login } from './components/Login/Login';
import { Register } from './components/Login/Register';
import { Collection } from './components/Collection/Collection';
import { Manager } from './components/Manager/Manager';
import { Employee } from './components/Employee/Employee';
import Myvideo from './galaxy.mp4';
import { Newreim } from './components/Login/Newreim';
import { Reimcollection } from './components/Collection/Reimcollection';
import { Pendingownreim } from './components/Collection/Pendingownreim';
import { Pendingreim } from './components/Collection/Pendingreim';
import { Ownreim } from './components/Collection/ownreim';


//...then you should leave the path as ""
function App() {
  
  
  return (
    <div className="App">
      <nav>
        <h1>Reimbursement System</h1>
      </nav>
      <video src={Myvideo} autoPlay loop muted />
      <BrowserRouter>
          <Routes>
              <Route path="" element={<Login/>}/>
              <Route path="/register" element={<Register/>}/>
              <Route path="/collection" element={<Collection/>}/>
              <Route path="/manager" element={<Manager/>}/>
              <Route path="/employee" element={<Employee/>}/>
              <Route path="/reimcollection" element={<Reimcollection/>}/>
              <Route path="/newreim" element={<Newreim/>}/>
              <Route path="/pendingownreim" element={<Pendingownreim/>}/>
              <Route path="/pendingreim" element={<Pendingreim/>}/>
              <Route path="/ownreim" element={<Ownreim/>}/>
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;