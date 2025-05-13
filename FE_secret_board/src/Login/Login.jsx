import React, { useState } from "react";
import { Button } from "react-bootstrap";
// import api from "../api/axios"; 

function Login() {


  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  // id, password를 useState로 관리
  const idHandler = (e) => {
    console.log(">>>debug Login idHandler");
    setId(e.target.value);
    console.log(`input id ${id}`);
  }

  const passwordHandler = (e) => {
    console.log("debug >>> Login passwordHandler");
    setPassword(e.target.value);
    console.log(`input password ${password}`);
  }


  // enter 로 눌러도 되고, submit 버튼을 눌러도 되게 하기 위해서 form 태그를 사용
  // form 태그의 onSubmit 이벤트를 사용
  const loginHandler = async (e, id, password) => {
    e.preventDefault(); // 기본 동작인 새로고침 방지
    console.log("debug >>> Login loginHandler");
    try {
      const data = {
        id,
        password
      };
      // const response = await api.post("/login", data); // api.js에서 axios.post로 요청
      // console.log("debug >>> response", response);
    } catch (err) {
      console.log("debug >>> Login failed",err);
    }

  }




  return (
    <div className="container mt-5">
      <div className="row justify-content-center"> {/* 가운데 정렬 */}
        <div className="col-md-6"> {/* 너비 조절 */}

          <h1 className="text-center">비밀 게시판</h1>
          <form onSubmit={(e) => { loginHandler(e, id, password) }}> {/* id, password를 loginHandler로 넘겨줌 */}
            <div className="mb-4">  {/* 간격 조절 */}
              <label className="ms-2">Id</label>
              <input type="text"  placeholder="Id" className="form-control" value={id} onChange={idHandler} />
            </div>

            <div className="mb-5">
              <label className="ms-2">Password</label>
              <input type="password"  placeholder="Password" className="form-control" value={password} onChange={passwordHandler} />
            </div>

            {/* <div class="d-grid gap-2 col-6 mx-auto"> */}  {/* 가운데 좁게 정렬 */}
            <div className="d-grid gap-2 mb-4">  {/* 가운데 길게 정렬 */}
              <Button variant="primary" size="lg" type="submit">Login</Button>
            </div>
            <div className="d-grid gap-2 mb-5">
              <Button variant="primary" size="lg" type="button">SignUp</Button>
            </div>
          </form>

        </div>
      </div>
    </div>
  );
}
export default Login;