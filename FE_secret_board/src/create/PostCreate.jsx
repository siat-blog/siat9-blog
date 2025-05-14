import React, { use } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";

function PostCreate() {

    // Context에서 id를 가져오기
    // const { id } = useContext(UserIdContext);
    const id = 1; // 임시 id 값

    const [title, setTitle] = React.useState("");
    const [content, setContent] = React.useState("");
    const moveUrl = useNavigate();

    const submitHandler = async (e) => {
        e.preventDefault();
        console.log("PostCreate > submitHandler");

        if (window.confirm("게시물을 등록하시겠습니까??")) {
            const response = await api.post(`/api/post/${id}`, {
                title: title,
                content: content,
            });
            if (response.status === 200) {
                alert("게시물을 등록했습니다.");
                moveUrl("/PostList");
            }
            else {
                alert("게시물 등록에 실패했습니다.");
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
