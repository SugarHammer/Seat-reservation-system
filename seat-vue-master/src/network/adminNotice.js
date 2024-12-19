import {request} from "./request";

export function adminNoticeData(req) {
    return request({
        method: 'post',
        url: '/notice/get/list',
        data: {
            "currentPage": req.currentPage,
            "title": req.name,
            "pageSize": req.pageSize
        }
    })
}

export function addNotice(form) {
    return request({
        method: 'post',
        url: '/notice',
        data: {
            "content": form.content,
            "title": form.title
        }
    })
}

export function deleteBatchNotice(ids) {
    return request({
        method: 'delete',
        url: '/notice/delete',
        data: {
            "ids": ids
        }
    })
}

export function updateNotice(form) {
    return request({
        method: 'put',
        url: '/notice/update',
        data: {
            "id": form.id,
            "title": form.title,
            "content": form.content
        }
    })
}