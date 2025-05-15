import React from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";


function PostItem(props) {
    const moveUrl = useNavigate() ;

    const detailViewHandler = async (id) => {
        console.log("debug >>> detailViewHandler")
        const response = await api.get(`/api/post/${id}`)
        console.log(response.data)
        moveUrl("/postread", { state : response.data} )
    }

    return (
        <tr>
            <td>
                <span className="hover-underline"
                onClick={()=>detailViewHandler(props.data.id)}>
                {props.data.title}</span>
                </td>
            <td>{props.data.author}</td>
            
        </tr>
    );
}

export default PostItem