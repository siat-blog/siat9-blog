import React from 'react';
import { BrowserRouter , Route, Routes} from "react-router-dom";
import { BootstrapProvider } from 'react-bootstrap';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from "./Login/Login"
import SignUp from "./SignUp/SignUp"

function App() {
  return (
    <BrowserRouter>
      {/* <h2>openapi 위한 라우터 연습</h2> */}
      <Routes>
        {/* 라우터당 컴포넌트 연결 */}
        <Route path="/"             element={<Login />}></Route>  
        <Route path="/SignUp"       element={<SignUp />}></Route>  
      </Routes>
    </BrowserRouter>
  );
}

export default App;