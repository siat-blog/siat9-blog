import React from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";

function PostRead(props) {
    const moveUrl = useNavigate();
    const data  = useLocation().state;
    const deleteHandler = async (e) => {
        e.preventDefault();
        console.log("PostRead > deleteHandler");

        if (window.confirm("게시물을 삭제하시겠습니까??")) {
            await api.delete(`api/post/`)
                .then((res) => {
                    console.log(res);
                    if (res.status === 200) {
                        alert("게시물이 삭제되었습니다.");
                        moveUrl("/PostList");
                    } else {
                        alert("게시물 삭제에 실패했습니다.");
                    }
                })
                .catch((err) => {
                    console.log(err);
                });
        } else {
        }
    }
    const moveToPostUpdate = (e) => {
        e.preventDefault();
        moveUrl('/PostUpdate', { state: { data: data } });

    }

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
                    <p style={{ height: "300px", overflowY: "scroll" }}>{data.content}</p>
                    </div>
                </div>
                <div style={{ textAlign: "right" }}>
                    <button type="submit" className="btn btn-primary" onClick={moveToPostUpdate}>수정</button>
                    <button type="submit" className="btn btn-primary mx-2" onClick={deleteHandler}>삭제</button>
                </div>
            </form>
        </div>
    );
}

export default PostRead;