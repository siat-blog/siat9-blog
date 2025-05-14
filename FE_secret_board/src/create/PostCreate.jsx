import React, { use } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";
import { createContext } from 'react';

function PostCreate() {
    const [title, setTitle] = React.useState("");
    const [content, setContent] = React.useState("");
    const [nickname, setNickname] = React.useState("null");
    const [date, setDate] = React.useState("YYYY-MM-DD");
    const id = "id";

    const moveUrl = useNavigate();

    const submitHandler = async (e) => {
        e.preventDefault();
        console.log("PostCreate > submitHandler");

        if (window.confirm("게시물을 등록하시겠습니까??")) {
            await api.post(`/api/post/${id}`, {
                title: title,
                content: content,
            });
        } else {
            moveUrl("/PostList");
        }
    };

    return (
        <div>
            <form className="mx-3 mt-3">
                <div className="d-flex justify-content-between">
                    <p className="mx-1">{nickname}</p>
                    <p className="mx-1">{date}</p>
                </div>
                <div className="mb-3">
                    <input
                        type="text"
                        placeholder="제목"
                        className="form-control"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <textarea
                        className="form-control"
                        placeholder="내용"
                        style={{ height: "300px" }}
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                    />
                </div>
                <div style={{ textAlign: "right" }}>
                    <button
                        type="submit"
                        className="btn btn-primary"
                        onClick={submitHandler}
                    >
                        등록
                    </button>
                </div>
            </form>
        </div>
    );
}

export default PostCreate;
