import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
// import PostList from './postList/PostList';
// import PostCreate from './create/PostCreate';
// import SignUp from './SignUp/SignUp';
// import Login from './Login/Login';
// import PostRead from './postread/PostRead';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './App';
// import UserContextProvider from './context/UserContextProvider';
// import LoginUserContextProvider from "./context/LoginUserContextProvider";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);


// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <LoginUserContextProvider>
//       <App />
//     </LoginUserContextProvider>
//   </React.StrictMode>
// );

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <LoginUserContextProvider>
//       <App />
//     </LoginUserContextProvider>
//   </React.StrictMode>
// );

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <LoginUserContextProvider>
//       <App />
//     </LoginUserContextProvider>
//   </React.StrictMode>
// );


// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <UserContextProvider>
//       <Login /> 
//       </UserContextProvider>
//   </React.StrictMode>
// );

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
