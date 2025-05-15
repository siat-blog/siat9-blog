import React from "react";

function PostItem(props) {

    const detailViewHandler = () => {
        console.log("debug >>> detailViewHandler")

    }

    return (
        <tr>
            <td><span className="hover-underline" onClick={detailViewHandler}>{props.data.title}</span></td>
            <td>{props.data.author}</td>
            
        </tr>
    );
}

export default PostItem