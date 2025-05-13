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
            <form className="mx-3 mt-3">
                <div className="d-flex justify-content-between">
                    <label className="form-label">닉네임</label>
                    <label className="form-label">YYYY-MM-DD</label>
                </div>
                <div className="mb-3">
                    <div className="form-floating">
                        <h4 className="">제목</h4>
                    </div>
                </div>
                <div className="mb-3">
                    <div className="form-floating">
                    <p style={{ height: "300px", overflowY: "scroll" }}>내용</p>
                    </div>
                </div>
                <div style={{ textAlign: "right" }}>
                    <button type="submit" className="btn btn-primary" onClick={updateHandler}>수정</button>
                    <button type="submit" className="btn btn-primary mx-2" onClick={deleteHandler}>삭제</button>
                </div>
            </form>
        </div>
    );
}

export default PostRead;