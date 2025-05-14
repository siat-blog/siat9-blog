import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom" ;
import PostItem from "./PostItem";
import api from "../api/axios";


function PostList(props) {

    const moveUrl = useNavigate() ;

    // 검색어 - 백엔드랑. 언제 될까?
    const [searchWords, setSearchWords] = useState("") ;

    // 전체 목록 리스트 - 백엔드랑. 언제 될까?
    const [postList, setList] =  useState([]) ;

    // 리스트 업데이트 - 백엔드랑. 언제 될까?
    useEffect(() => {
        console.log("debug >>>> useEffect >>>>>>>>>>>>>>>>>> ") ;
        getList() ;
    }, []) ;

    const getList = async () => {
        console.log("debug >>>>> getList endpoint: / react / list")
        const response = await api.get("/api/board")   // request path
        console.log("response:", response)
        console.log(`response status: ${response.status}` )
        console.log(`response data: ${response.data}`)
        setList(response.data);
    }

    // 로그아웃 버튼 - FE 은영
    const logoutHandler = () => {
        console.log("debug >>> logoutHandler")
        // 콘텍스트를 끊어버리고 루트 페이지로 이동
        moveUrl("/")
    }

    // 검색어 입력 - 백엔드랑. 언제 될까?
    const searchInputHandler = (event) => {
        console.log("debug >>> searchHandler serachWords: ", searchWords)
        setSearchWords(event.target.value) ;
             
    }

    // 검색 버튼 - 백엔드랑. 언제 될까?
    const searchHandler = async () => {
        console.log("debug >>> searchHandler serachWords: ", searchWords)
        const response = await api.post("/api/board")   // 검색 기능 리퀘스트 패스? Post야 get이야?
        setList(response.data);
    }

    // 라우터에 작성 페이지 등록하기만 하면 됨. FE 병윤.
    const postCreateHandler = () => {
        console.log("debug >>> postCreateHandler")
        moveUrl() // 글 작성 페이지로 이동. 글 작성 request path 필요
    }
    
    return (  
        <div>
            
            <div className="d-flex justify-content-between align-items-center mb-3">
                <h1 className="m-0 ms-5 mt-4 mb-2">{props.teamName}의 게시판</h1>
                <button type="button"
                 className="btn btn-link mt-4 me-5 mb-3"
                 onClick={logoutHandler}>Log out</button>
            </div>

            <h3 className="ms-5 mb-4">{props.userName}님 환영합니다</h3>
            
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
                        <th>게시물</th>
                        <th>조회수</th>
                        <th>작성일</th>
                        
                    </tr>
                    </thead>
                    <tbody>
                        {  postList.map( singlePost => {
                            return (
                                <PostItem key={singlePost.seq}
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