import React from "react";

function PostItem(props) {

    const detailViewHandler = () => {
        console.log("debug >>> detailViewHandler")

    }

    return (
        <tr>
            <td><span className="hover-underline" onClick={detailViewHandler}>{props.data.postTitle}</span></td>
            <td>{props.data.postViews}</td>
            <td>{props.data.postDate}</td>
            
        </tr>
    );
}

export default PostItem