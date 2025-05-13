import React, { useState } from "react";

function PostList(props) {
    const [searchWords, setSearchWords] = useState("") ;

    const logoutHandler = () => {
        console.log("debug >>> logoutHandler")
    }

    const searchHandler = (event) => {
        console.log("debug >>> searchHandler serachWords: ", searchWords)
        setSearchWords(event.target.value) ;
        
        }

        
    const postCreateHandler = () => {
        console.log("debug >>> postCreateHandler")
    }
    
    return (  
        <div>
            
            <div className="d-flex justify-content-between align-items-center mb-3">
                <h1 className="m-0 ms-5 mt-4 mb-2">{props.teamName}의 게시판</h1>
                <button type="button"
                 className="btn btn-link mt-4 me-5 mb-3"
                 onClick={logoutHandler}>Log out</button>
            </div>

            <h3 className="ms-5 mb-3">{props.userName}님 환영합니다</h3>
            
            <div className="d-flex justify-content-between align-items-center col-md-4">
                <input type="text"
                    className="form-control col-md-6 ms-5 mb-3"
                    placeholder="검색어를 입력하세요"
                    onChange={searchHandler}
                ></input>
                <button type="button"
                className="btn btn-primary ms-3 col-md-2 mb-3"
                onClick={searchHandler}>검색</button>
                <button type="button"
                className="btn btn-primary col-md-3 ms-3 mb-3"
                onClick={postCreateHandler}>게시물 작성</button>
            </div>
            

            <ul className="list-group">
                <li className="list-group-item">
                    <div className="row">
                        <div className="col-4 text-center"><strong>글 제목</strong></div>
                        <div className="col-4 text-center"><strong>조회 수</strong></div>
                        <div className="col-4 text-center"><strong>작성일</strong></div>
                    </div>
                </li>
                <li className="list-group-item">
                    <div className="row">
                        <div className="col-4 text-center"><strong>-</strong></div>
                        <div className="col-4 text-center"><strong>-</strong></div>
                        <div className="col-4 text-center"><strong>-</strong></div>
                    </div>
                </li>
                
            </ul>
        </div>
    )}

export default PostList ;