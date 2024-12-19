import {request} from "./request";

export function adminClassData(req) {
    return request({
        method: 'put',
        url: '/admin/class/find/admin/list',
        data: {
            "currentPage": req.currentPage,
            "name": req.name,
            "pageSize": req.pageSize
        }
    })
}

export function deleteBatch(ids) {
    return request({
        method: 'delete',
        url: '/admin/class/delete',
        data: {
           "ids" :ids
        }
    })
}

export function addClass(form) {
    return request({
        method: 'post',
        url: '/admin/class',
        data: {
            "closeTime": form.openTime[1],
            "isOpen": form.isOpen,
            "name": form.name,
            "openTime": form.openTime[0],
            "rows": form.row,
            "totalNum": form.totalNum
        }
    })
}

export function updateClass(form) {
    return request({
        method: 'put',
        url: '/admin/class/update',
        data: {
            "id":form.id,
            "closeTime": form.openTime[1],
            "isOpen": form.isOpen,
            "name": form.name,
            "openTime": form.openTime[0],
            "rows": form.rows,
            "totalNum": form.totalNum
        }
    })
}