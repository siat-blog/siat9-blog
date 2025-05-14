import axios from "axios";
import { useContext } from "react";

const api = axios.create({
    baseURL : 'http://localhost:8088/'
});

// access token을 헤더에 추가
api.interceptors.request.use(
    (config) => {
        const { accessToken} = useContext(UserContext);
        if(accessToken) {
            config.headers.Authorization = accessToken ;
        }
        return config ;
    }
);

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

export default api;