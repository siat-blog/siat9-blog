import React, { useState } from "react";
import UserContext from "./UserContext";

function LoginUserContextProvider({ children }) {
    const [loginUser, setLoginUser] = useState({
        memberId: "",
        // userName: "",      // PostList에 들어갈 닉네임 또는 이름
        // teamName: "",      // PostList에 들어갈 팀 이름
        accessToken: "",
        refreshToken: ""
      });

  return (
    <UserContext.Provider value={{ loginUser, setLoginUser }}>
      {children}
    </UserContext.Provider>
  );
}

export default LoginUserContextProvider;