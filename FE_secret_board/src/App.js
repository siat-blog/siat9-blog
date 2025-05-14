import { BrowserRouter , Route, Routes} from "react-router-dom";
import './App.css';

import Login from "./Login/Login"
import SignUp from "./SignUp/SignUp"
import PostRead from "./postread/PostRead";
import PostCreate from "./create/PostCreate";

function App() {
  return (
    <BrowserRouter>
      {/* <h2>openapi 위한 라우터 연습</h2> */}
      <Routes>
        {/* 라우터당 컴포넌트 연결 */}
        <Route path="/"             element={<Login />}></Route>  
        <Route path="/SignUp"       element={<SignUp />}></Route>  
        <Route path="/PostRead"     element={<PostRead />}></Route>  
        <Route path="/PostCreate"   element={<PostCreate />}></Route>  

      </Routes>
    </BrowserRouter>
  );
}

export default App;






// import logo from './logo.svg';
// import './App.css';

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

// export default App;
