// UserContext는 사용자 정보를 관리하는 Context 객체로 뭐든지 담을 수 있는 컨테이너 역할을 한다.
// 이 객체를 사용하여 컴포넌트 간에 사용자 정보를 공유할 수 있다.

import { createContext } from "react";
const LoginUserContext = createContext(null); // Context 객체 생성
export default LoginUserContext; // Context 객체 내보내기