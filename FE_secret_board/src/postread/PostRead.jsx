import React from "react";

function PostRead(props) {
    const [title, setTitle] = React.useState("");
    const [content, setContent] = React.useState("");

    const deleteHandler = (e) => {
        e.preventDefault();
        console.log("PostRead > deleteHandler");

        if (window.confirm("게시물을 삭제하시겠습니까??")) {
            // api 요청
        } else {
        }
    }
    const updateHandler = (e) => {
        e.preventDefault();
        console.log("PostRead > updateHandler");
    }

    return (
        <div>
            <form class="mx-3 mt-3">
                <div class="d-flex justify-content-between">
                    <label class="form-label">닉네임</label>
                    <label class="form-label">YYYY-MM-DD</label>
                </div>
                <div class="mb-3">
                    <div class="form-floating">
                        <h4 class="">제목</h4>
                    </div>
                </div>
                <div class="mb-3">
                    <div class="form-floating">
                    <p style={{ height: "300px", overflowY: "scroll" }}>내용</p>
                    </div>
                </div>
                <div style={{ textAlign: "right" }}>
                    <button type="submit" class="btn btn-primary" onClick={updateHandler}>수정</button>
                    <button type="submit" class="btn btn-primary mx-2" onClick={deleteHandler}>삭제</button>
                </div>
            </form>
        </div>
    );
}

export default PostRead;