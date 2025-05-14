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
    console.log(">>>debug SignUp nicknameHandler");
    setNickName(e.target.value);
    console.log(`input nickName ${nickName}`);
  }

  const idHandler = (e) => {
    console.log(">>>debug SignUp idHandler");
    setId(e.target.value);
    console.log(`input id ${id}`);
  }

  const passwordHandler = (e) => {
    console.log("debug >>> SignUp passwordHandler");
    setPassword(e.target.value);
    console.log(`input password ${password}`);
  }

  const teamHandler = (e) => {
    console.log(">>>debug SignUp teamHandler");
    setTeam(e.target.value);
    console.log(`input team ${team}`);
  }


  // enter 로 눌러도 되고, submit 버튼을 눌러도 되게 하기 위해서 form 태그를 사용
  // form 태그의 onSubmit 이벤트를 사용
  const signUpHandler = async (e, id, password) => {
    e.preventDefault(); // 기본 동작인 새로고침 방지

    // 드롭다운은 required 속성이 안 먹어서 수동체크를 해야 한다. ->if문으로 체크
    // 팀을 선택하지 않으면 alert 띄우기
    if (!team) {  
      alert("팀을 선택하세요");
      return;
    }

    console.log("debug >>> SignUp signUpHandler");
    try {
      const data = {
        "memberNickname" : nickName,
        "memberId" : id,
        "memberPassword" : password,
        "boardType" : team
      };
      // const response = await api.post("/api/member/signup", data); // api.js에서 axios.post로 요청
      // console.log("debug >>> 회원가입 성공");
      // console.log("debug >>> response", response);
    } catch (err) {
      console.log("debug >>> 회원가입 실패",err);
    }

  }




  return (
    <div className="container mt-5">
      <div className="row justify-content-center"> {/* 가운데 정렬 */}
        <div className="col-md-6"> {/* 너비 조절 */}

          <h1 className="text-center">회원 가입</h1>
          {/* nickName, id, password, team를 signUpHandler 넘겨줌 */}
          <form onSubmit={(e) => { signUpHandler(e, nickName, id, password, team) }}> 
          <div className="mb-3">
              <label className="ms-2">NickName</label>
              <input type="text"  placeholder="NickName" className="form-control" value={nickName} onChange={nicknameHandler} required/>
            </div>
            
            
            <div className="mb-3">  {/* 간격 조절 */}
              <label className="ms-2">Id</label>
              <input type="text"  placeholder="Id" className="form-control" value={id} onChange={idHandler} required/>
            </div>

            <div className="mb-3">
              <label className="ms-2">Password</label>
              <input type="password"  placeholder="Password" className="form-control" value={password} onChange={passwordHandler} required/>
            </div>

            <div className="mb-5">
              <label className="ms-2">Team</label>
              {/* <input type="text"  placeholder="Team" className="form-control" value={team} onChange={teamHandler} /> */}
              <div className="dropdown">
              <button className="btn btn-secondary dropdown-toggle  w-100" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                {team || "팀을 선택하세요"}
              </button>

                <ul className="dropdown-menu w-100">
                  <li><button className="dropdown-item" type="button" onClick={() => setTeam("엘프족")}>엘프족</button></li>
                  <li><button className="dropdown-item" type="button" onClick={() => setTeam("드래곤족")}>드래곤족</button></li>
                  <li><button className="dropdown-item" type="button" onClick={() => setTeam("호빗족")}>호빗족</button></li>
                </ul>
              </div>
            </div>


            {/* 가운데 길게 정렬 */}
            {/* <div className="d-grid gap-2">  
              <Button variant="primary" size="lg" type="submit">Login</Button>
            </div> */}


            {/* 사용 안 한다.<div class="d-grid gap-2 col-6 mx-auto"> */}  {/* 가운데 좁게 정렬 */}
            
            {/* //로그인 페이지로 이동 */}
            {/* 가운데 길게 정렬 */}
            <div className="d-grid gap-2">  
              <Button variant="primary" size="lg" type="submit" onClick={ () => {
                moveUrl("/");  
              }}>Login</Button>
            </div>
          </form>

        </div>
      </div>
    </div>
  );
}
export default SignUp;