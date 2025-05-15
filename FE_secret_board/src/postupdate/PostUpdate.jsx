import React from "react";
import api from "../api/axios";
import { useNavigate, useLocation } from "react-router-dom";
import { useState, useEffect } from "react";

function PostUpdate() {

    // localStorage에서 가져오기
    // const id = localStorage.getItem("id");
    const id = 1; // 임시 id 값

    const moveUrl = useNavigate();
    const location = useLocation();
    const data = location.state?.data;

    const [title, setTitle] = useState(data?.title || "");
    const [content, setContent] = useState(data?.content || "");

    useEffect(() => {
        if (!data) {
            alert("수정할 게시물 정보가 없습니다.");
            moveUrl("/PostList");
        }
    }, [data, moveUrl]);

    const updateHandler = async (e) => {
        e.preventDefault();
        console.log("PostUpdate > updateHandler");

        if (window.confirm("수정 사항을 저장합니까?")) {
            const response = await api.put(`/api/post/${id}`, {
                title: title,
                content: content,
            });
            if (response.status === 200) {
                alert("게시물이 수정되었습니다.");
                moveUrl("/PostList");
            } else {
                alert("게시물 수정에 실패했습니다.");
                console.log(response);
                console.log(response.status);
                moveUrl("/PostList");
            }
        } else {
            moveUrl("/PostList");
        }
    };

    return (
        <div>
            <form className="mx-3 mt-3">
                <div className="mb-3">
                    <input
                        type="text"
                        placeholder="제목"
                        className="form-control"
                        value={title}
                        required="text"
                        onChange={(e) => setTitle(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <textarea
                        className="form-control"
                        placeholder="내용"
                        style={{ height: "300px" }}
                        value={content}
                        required="text"
                        onChange={(e) => setContent(e.target.value)}
                    />
                </div>
                <div style={{ textAlign: "right" }}>
                    <button
                        type="submit"
                        className="btn btn-primary"
                        onClick={updateHandler}
                    >
                        수정
                    </button>
                </div>
            </form>
        </div>
    );
}

export default PostUpdate;
