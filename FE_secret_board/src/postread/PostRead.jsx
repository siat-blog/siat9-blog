import React from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";

function PostRead() {
    const moveUrl = useNavigate();

    // localStorage에서 가져오기
    // const id = localStorage.getItem("id");
    // const id = 1; // 임시 id 값

    // 임시 데이터 (향후 useLocation으로 대체)
    // const data = {title: "test title",   // 임시 데이터
    //               content: "test content",
    //               nickname: "test name",
    //               date: "YYYY-MM-DD"
    //             }

    const location = useLocation();
    const data = location.state ;

    const deleteHandler = async (e) => {
        e.preventDefault();
        console.log("PostRead > deleteHandler");

        if (window.confirm("게시물을 삭제하시겠습니까??")) {
            const response = await api.delete(`api/post/${id}`)
            if (response.status === 200) {
                alert("게시물이 삭제되었습니다.");
                moveUrl("/PostList");
            } else {
                alert("게시물 삭제에 실패했습니다.");
                console.log(response);
                console.log(response.status);
            }
        } else {
        }
    }
    const moveToPostUpdate = (e) => {
        e.preventDefault();
        moveUrl('/PostUpdate', { state: { data: data } });
    }

    const goBack = () => {
        moveUrl("/PostList");
    };

    return (
        <div>
            <form className="mx-3 mt-3">
                <div className="d-flex justify-content-between">
                    <p>{data.nickName}</p>
                    <p>{data.date}</p>
                </div>
                <div className="mb-3">
                    <div className="form-floating">
                        <h4 className="">{data.title}</h4>
                    </div>
                </div>
                <div className="mb-3">
                    <div className="form-floating">
                    <p style={{ height: "200px", overflowY: "scroll" }}>{data.content}</p>
                    </div>
                </div>
                <div className="d-flex justify-content-between">
                    <button type="button" className="btn btn-secondary" onClick={goBack}>뒤로</button>
                    <div>
                        <button type="submit" className="btn btn-primary" onClick={moveToPostUpdate}>수정</button>
                        <button type="button" className="btn btn-danger mx-2" onClick={deleteHandler}>삭제</button>
                    </div>
                </div>
            </form>
        </div>
    );
}

export default PostRead;