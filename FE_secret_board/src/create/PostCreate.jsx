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
            <form class="mx-3 mt-3">
                <div class="    d-flex justify-content-between">
                    <label class="form-label">닉네임</label>
                    <label class="form-label">YYYY-MM-DD</label>
                </div>
                <div class="mb-3">

                        <input type="text" placeholder="Title" class="form-control" id="floatingTitle" />
                        <label for="">Title</label>

                </div>
                <div class="mb-3">
                    <div class="">
                        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea" style={{ height: "300px" }}></textarea>
                        <label for="">Content</label>
                    </div>
                </div>
                <div style={{ textAlign: "right" }}>
                    <button type="submit" class="btn btn-primary" onClick={submitHandler}>등록</button>
                </div>
            </form>
        </div>
    );
}

export default PostCreate;