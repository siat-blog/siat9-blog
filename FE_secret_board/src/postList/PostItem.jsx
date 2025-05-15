import React from "react";
import { useNavigate } from "react-router-dom";


function PostItem(props) {
    const moveUrl = useNavigate() ;

    const detailViewHandler = () => {
        console.log("debug >>> detailViewHandler")
        moveUrl("/postread", { state : props.data.id})
    }

    return (
        <tr>
            <td><span className="hover-underline" onClick={detailViewHandler}>{props.data.title}</span></td>
            <td>{props.data.author}</td>
            
        </tr>
    );
}

export default PostItem