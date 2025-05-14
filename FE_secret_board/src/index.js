import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
// import App from './App';
import reportWebVitals from './reportWebVitals';
import PostList from './postList/PostList';
import PostCreate from './create/PostCreate';
import SignUp from './SignUp/SignUp';
import Login from './Login/Login';
import PostRead from './postread/PostRead';
import 'bootstrap/dist/css/bootstrap.min.css';
import SignUp from './SignUp/SignUp';

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <SignUp />
  </React.StrictMode>
);


reportWebVitals();
