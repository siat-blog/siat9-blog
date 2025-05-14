import React, { use } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";

function PostUpdate() {
  const [title, setTitle] = useState(data?.title || "");
  const [content, setContent] = useState(data?.content || "");
    const moveUrl = useNavigate();

      const location = useLocation();
  const { data } = location.state || {};  // PostRead에서 넘긴 data

    const updateHandler = async (e) => {
        e.preventDefault();
        console.log("PostUpdate > updateHandler");

        if (window.confirm("게시물을 수정하시겠습니까??")) {
            await api.put(`/api/post/`, {
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
