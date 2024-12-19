import {request} from "./request";

export function adminUserData(req) {
    return request({
        method: 'post',
        url: '/user/find/list',
        data: {
            "currentPage": req.currentPage,
            "username": req.name,
            "pageSize": req.pageSize
        }
    })
}

export function deleteBatchUser(ids) {
    return request({
        method: 'delete',
        url: '/user/deleted',
        data: {
            "ids" :ids
        }
    })
}

export function updateUser(form) {
    return request({
        method: 'put',
        url: '/user/update',
        data: {
            "id": form.id,
            "nickName": form.nickName,
            "password": form.password,
            "username": form.username
        }
    })
}