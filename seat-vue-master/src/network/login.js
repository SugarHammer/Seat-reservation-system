import {request} from "./request";


export function login(form) {
    return request({
        method: 'post',
        url: '/user/login',
        data: {
            "username": form.username,
            "password": form.password,
            "captcha": form.captcha,
            "captchaId": form.captchaId,
            "rememberMe": form.rememberme,
        }
    })
}

export function register(form) {
    return request({
        method: 'post',
        url: '/user/register',
        data: {
            "username": form.username,
            "password": form.password,
            "checkPassword": form.checkPassword,
            "checkCode": form.checkCode,
            "nickName": form.nickName,
        }
    })
}

export function logout() {
    return request({
        method: 'get',
        url: '/user/logout'
    })
}

export function getUserInfo() {
    return request({
        method: 'get',
        url: '/user/find/current/info'
    })
}