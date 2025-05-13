import React from "react";

function PostCreate() {
  return (
    <div>
        <form class="mx-3 mt-3">
            <div class="mx-1 d-flex justify-content-between">
                <label class="form-label">R!</label>
                <label class="form-label">YYYY-MM-DD</label>
            </div>
            <div class="mb-3">
                <div class="form-floating">
                    <input type="text" placeholder="Title" class="form-control" id="floatingTitle"/>
                    <label for="floatingTitle">Title</label>
                </div>
            </div>
            <div class="mb-3">
                <div class="form-floating">
                    <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea" style={{height: "300px"}}></textarea>
                    <label for="floatingTextarea">Content</label>
                </div>
            </div>
            <div style={{textAlign: "right"}}>
                <button type="submit" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
  );
}

export default PostCreate;