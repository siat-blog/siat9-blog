import React, { useState , useEffect , useContext  } from "react";
import { Button } from "react-bootstrap";
import api from "../api/axios"; 
import {useNavigate} from "react-router-dom";
// import UserContext from "../context/UserContext";
import LoginUserContext from "../context/LoginUserContext";
 


function Login(props) {

  
  useEffect(() => {
    console.log("debug >>> component mount");
  }, []);

  const { setLoginUser } = useContext(LoginUserContext); // UserContext에서 setUserInfo 가져오기

  // useNavigate 훅 이용해서 페이지 이동
  const moveUrl = useNavigate();

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
        "id" : id,
        "password" : password
      };
      const response = await api.post("/api/auth/login", data); // api.js에서 axios.post로 요청
      console.log("debug >>> 로그인 성공");
      console.log("debug >>> response", response);
      console.log("debug >>> response", response.data);
      console.log("debug >>> response", response.data.token);

      // 로그인 성공시, 토큰 관련
      // const token = response.data.token; // 토큰 저장
      // localStorage.setItem("token", token); // 로컬 스토리지에 저장
      // console.log("debug >>> token", token);

      // const { accessToken, refreshToken } = response.data;
      // localStorage.setItem("accessToken", response.headers.authorization);
      // localStorage.setItem("refreshToken", response.headers.refreshToken);

      
      
      // 로그인 성공시, 사용자 정보 저장 
      console.log("debug >>> 로그인 성공 후 사용자 정보", response.headers['authorization']);

      //✅ 1단계: 헤더에서 토큰 꺼내기
      const authorizationHeader = response.headers['authorization']; // 보통 "Bearer {토큰값}" 형태
      console.log("debug >>> 헤더에서 추출한 토큰:", authorizationHeader);

      //✅ 2단계: "Bearer " substring(7)은 "Bearer "(공백 포함 7자)를 제거하는 작업입니다.
      const extractedToken = authorizationHeader?.startsWith("Bearer ")
      ? authorizationHeader.substring(7)
      : authorizationHeader;

      // const { accessToken, refreshToken } = response.data;
      setLoginUser({
        memberId: id,
        // userName: response.data.memberNickname, // ✅ 백엔드 필드명 확인 , PostList 작성 때 불필요하면 지우기
        // teamName: response.data.boardType,      // ✅ 백엔드 필드명 확인
        // accessToken: response.data.accessToken,
        accessToken: extractedToken, // ✅ 3단계: 헤더에서 가져온 accessToken 사용
        refreshToken: response.data.refreshToken
        
        // accessToken: accessToken,
        // refreshToken: refreshToken,
      });


      // 로그인 성공 후 이동할 페이지 
      moveUrl("/PostList"); 

    } catch (err) {
      console.log("debug >>> 로그인 실패",err);
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
              <input type="text"  placeholder="Id" className="form-control" value={id} onChange={idHandler} required/>
            </div>

            <div className="mb-5">
              <label className="ms-2">Password</label>
              <input type="password"  placeholder="Password" className="form-control" value={password} onChange={passwordHandler} required/>
            </div>

            {/* 가운데 좁게 정렬 */}
            {/* <div class="d-grid gap-2 col-6 mx-auto"> */}  

            {/* 가운데 길게 정렬 */}
            <div className="d-grid gap-2 mb-4">  
              <Button variant="primary" size="lg" type="submit">Login</Button>
            </div>
            {/* <div className="d-grid gap-2 mb-5">
              <Button variant="primary" size="lg" type="button">SignUp</Button>
            </div> */}

            {/* 회원가입 페이지로 이동 라우터 작성 */}
            <div className="d-grid gap-2 mb-5">
              <Button variant="primary" size="lg" type="button" onClick={ () => {
                moveUrl("/SignUp");  
              }}>SignUp</Button>
            </div>
          </form>

        </div>
      </div>
    </div>
  );
}
export default Login;