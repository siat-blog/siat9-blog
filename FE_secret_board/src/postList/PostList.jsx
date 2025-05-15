import React, { useEffect, useState } from "react"; 
import { useNavigate } from "react-router-dom" ;
import PostItem from "./PostItem";
import api from "../api/axios";
import {jwtDecode} from 'jwt-decode';
// import LoginUserContext from "../context/LoginUserContext"; // ✅ Context import

function PostList(props) {

    // const { setLoginUser, loginUser } = useContext(LoginUserContext); //✅ 변경 권장 사항 (Context 값 직접 사용)

    const moveUrl = useNavigate() ;

    // 검색어 - 백엔드랑. 언제 될까?
    const [title, setSearchTitle] = useState("") ;

    // 전체 목록 리스트 - 백엔드랑. 언제 될까?
    const [postList, setList] =  useState([]) ;

    // 리스트 업데이트 - 백엔드랑. 언제 될까?
    useEffect(() => {
        console.log("debug >>>> useEffect >>>>>>>>>>>>>>>>>> ") ;
        getList() ;
    }, []) ;

    const getList = async () => {
        console.log("debug >>>>> getList endpoint: / react / list")
        const response = await api.get("/api/post/list")   // request path
        console.log("response:", response)
        console.log(`response status: ${response.status}` )
        console.log(`response data: ${response.data}`)
        setList(response.data);
    }

    // 로그아웃 버튼 - FE 은영
    const logoutHandler = () => {
        console.log("debug >>> logoutHandler")
        localStorage.removeItem("memberId");
        localStorage.removeItem("accessToken");
        localStorage.removeItem("refreshToken");
        // 콘텍스트를 끊어버리고 루트 페이지로 이동
        // ✅ Context 초기화
        // setLoginUser({
        //     memberId: "",
        //     accessToken: "",
        //     refreshToken: ""
        // });
        
        moveUrl("/")
    }

    // 검색어 입력 - 백엔드랑. 언제 될까?
    const searchInputHandler = (event) => {
        console.log("debug >>> searchHandler serachWords: ", title)
        setSearchTitle(event.target.value) ;
             
    }

    // 검색 버튼 - 백엔드랑. 언제 될까?
    const searchHandler = async () => {
        console.log("debug >>> searchHandler serachWords: ", title)
        const response = await api.get(`/api/post/search?title=${title}`)   // 검색 기능 리퀘스트 패스? Post야 get이야?
        setList(response.data);
    }

    // 라우터에 작성 페이지 등록하기만 하면 됨. FE 병윤.
    const postCreateHandler = () => {
        console.log("debug >>> postCreateHandler")
        moveUrl("/PostCreate") // 글 작성 페이지로 이동. 글 작성 request path 필요
    }
    const [userInfo, setUserInfo] = useState({});
        useEffect(() => {
        const userInfo = getUserInfoFromToken();
        if (userInfo) {
        console.log('로그인 사용자 정보:', userInfo);
        setUserInfo(userInfo);
        } else {
        console.log('토큰 없음 또는 잘못된 토큰');
        }
    }, []);
     function getUserInfoFromToken() {
        const token = localStorage.getItem('accessToken');
        if (!token) return null;

        try {
            const decoded = jwtDecode(token);
            const { memberId, memberNickName, boardGrade } = decoded;

            return { memberId, memberNickName, boardGrade};
        } catch (error) {
            console.error('Invalid token:', error);
            return null;
    }
}
    
    // 이제 로컬 스토리지에서 boardType이랑 memberNickname만 받아오면 됨
    
    return (  
        <div>
            
            <div className="d-flex justify-content-between align-items-center mb-3">
                <h1 className="m-0 ms-5 mt-4 mb-2">{userInfo.boardGrade}의 게시판</h1>

                <button type="button"
                 className="btn btn-link mt-4 me-5 mb-3"
                 onClick={logoutHandler}>Log out</button>  
            </div>

            {/* 로그아웃 클릭하면 로컬 스토리지 끊기 */}

            <h3 className="ms-5 mb-4">{userInfo.memberNickName}님 환영합니다</h3>
            
            <div className="d-flex justify-content-between align-items-center col-md-4">
                <input type="text"
                    className="form-control col-md-6 ms-5 mb-5"
                    placeholder="검색어를 입력하세요"
                    onChange={searchInputHandler}
                ></input>
                <button type="button"
                className="btn btn-primary ms-3 col-md-2 mb-5"
                onClick={searchHandler}>검색</button>
                <button type="button"
                className="btn btn-primary col-md-3 ms-3 mb-5"
                onClick={postCreateHandler}>게시물 작성</button>
            </div>
            
            <div align = "center">
                <table className="table">
                    <thead>
                    <tr>
                        <th>제목</th>
                        <th>작성자</th>
                        {/* <th>작성일</th> */} 
                    </tr>
                    </thead>
                    <tbody align = "center">
                        {  postList.map( singlePost => {
                            return (
                                <PostItem key={singlePost.id}
                                // 게시물 순번
                                          data={singlePost}
                                // 게시물 데이터
                                 />
                            );
                        })
                        }      
                    </tbody>
                </table>
            </div>
        </div>
    )}

export default PostList ;
