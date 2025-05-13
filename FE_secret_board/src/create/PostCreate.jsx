import React from "react";

function PostCreate() {
    const [title, setTitle] = React.useState("");
    const [content, setContent] = React.useState("");

    const submitHandler = (e) => {
        e.preventDefault();
        console.log("PostCreate > submitHandler");

        if (window.confirm("게시물을 등록하시겠습니까??")) {
            // api 요청
        } else {
        }
    }
    return (
        <div>
            <form className="mx-3 mt-3">
                <div className="    d-flex justify-content-between">
                    <label className="form-label">닉네임</label>
                    <label className="form-label">YYYY-MM-DD</label>
                </div>
                <div className="mb-3">
                    <input type="text" placeholder="제목" className="form-control"/>
                </div>
                <div className="mb-3">
                    <textarea className="form-control" placeholder="내용" style={{ height: "300px" }}></textarea>
                </div>
                <div style={{ textAlign: "right" }}>
                    <button type="submit" className="btn btn-primary" onClick={submitHandler}>등록</button>
                </div>
            </form>
        </div>
    );
}

export default PostCreate;