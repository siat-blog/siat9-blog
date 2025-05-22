import axios from "axios";
// import { useContext } from "react";
// import UserContext from "../context/UserContext";

const api = axios.create({
    baseURL : 'http://127.0.0.1:8088/',
    withCredentials : true, // CORS 요청 시 credentials(쿠키)를 포함하여 요청 
    // 서버와 클라이언트가 서로 다른 도메인(예: 포트가 다른 경우)일 때, 쿠키(세션, 인증용)를 주고받기 위해 사용
});

// access token을 헤더에 추가
// localstorage
api.interceptors.request.use(
    (config) => {
        const accessToken = localStorage.getItem("accessToken");
        if(accessToken) {
            config.headers.Authorization =  "Bearer "+accessToken ;
        }
        return config ;
    }
);


export default api;

// // access token 만료시 자동 재발급하여 재요청
// api.interceptors.response.use(
//     (response) => response, 
//     async (error) => {
//         const originalRequest = error.config;
//         if( error.response?.status === 403 && !originalRequest._retry) {
//             originalRequest._retry = true;
//             try {
//                 const { refreshToken, setAccessToken } = useContext(UserContext);
//                 const res = await axios.post('/', {}, {
//                                 baseURL: "http://localhost:8088/",
//                                 headers : {
//                                     Authorization : `Bearer ${refreshToken}`
//                                 },
//                                 withCredentials : true
//                             });
//                 const newAccessToken = res.headers.get("Authorization")
//                 setAccessToken(newAccessToken);
//                 // 재요청            
//                 originalRequest.headers.Authorization = `Bearer ${newAccessToken}` ;
//                 return api(originalRequest);
//             } catch(err) {
//                 return Promise.reject(err);
//             }
            
//         } // if end
//         return Promise.reject(error);          
//     }
// );

