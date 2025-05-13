import React, { useState } from "react";
import { Button } from "react-bootstrap";
// import api from "../api/axios"; 
import {useNavigate} from "react-router-dom";

function SignUp() {

  const moveUrl = useNavigate();

  // 밑의 value 와 똑같이 설정 , 핸들러 설정
  const [nickName, setNickName] = useState("");
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [team, setTeam] = useState("");


  // useState로 관리
  const nicknameHandler = (e) => {
    console.log(">>>debug Login nicknameHandler");
    setNickName(e.target.value);
    console.log(`input nickName ${nickName}`);
  }

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

  const teamHandler = (e) => {
    console.log(">>>debug Login teamHandler");
    setTeam(e.target.value);
    console.log(`input team ${team}`);
  }


  // enter 로 눌러도 되고, submit 버튼을 눌러도 되게 하기 위해서 form 태그를 사용
  // form 태그의 onSubmit 이벤트를 사용
  const loginHandler = async (e, id, password) => {
    e.preventDefault(); // 기본 동작인 새로고침 방지
    console.log("debug >>> Login loginHandler");
    try {
      const data = {
        nickName,
        id,
        password,
        team
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

          <h1 className="text-center">회원 가입</h1>

          <form onSubmit={(e) => { loginHandler(e, nickName, id, password, team) }}> {/* id, password를 loginHandler로 넘겨줌 */}
          <div className="mb-3">
              <label className="ms-2">NickName</label>
              <input type="text"  placeholder="NickName" className="form-control" value={nickName} onChange={nicknameHandler} />
            </div>
            
            
            <div className="mb-3">  {/* 간격 조절 */}
              <label className="ms-2">Id</label>
              <input type="text"  placeholder="Id" className="form-control" value={id} onChange={idHandler} />
            </div>

            <div className="mb-3">
              <label className="ms-2">Password</label>
              <input type="password"  placeholder="Password" className="form-control" value={password} onChange={passwordHandler} />
            </div>

            <div className="mb-5">
              <label className="ms-2">Team</label>
              <input type="text"  placeholder="Team" className="form-control" value={team} onChange={teamHandler} />
            </div>

            {/* <div class="d-grid gap-2 col-6 mx-auto"> */}  {/* 가운데 좁게 정렬 */}
            <div className="d-grid gap-2">  {/* 가운데 길게 정렬 */}
              <Button variant="primary" size="lg" type="submit" onClick={ () => {
                moveUrl("/");  //회원가입 페이지로 이동
              }}>Login</Button>
            </div>
          </form>

        </div>
      </div>
    </div>
  );
}
export default SignUp;