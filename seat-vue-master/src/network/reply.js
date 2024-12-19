import {request} from "./request";

export function getList(data) {
    return request({
        method: 'post',
        url: '/message/get/list',
        data:{
            "currentPage": data.currentPage,
            "pageSize": data.pageSize
        }
    })
}

export function sendMsg(content) {
    return request({
        method: 'post',
        url: '/message',
        data:{
            "content": content
        }
    })
}

export function reply(data) {
    return request({
        method: 'post',
        url: '/message/replay',
        data:{
            "id": data.id,
            "replay": data.content
        }
    })
}

export function deleteBatch(ids) {
    return request({
        method: 'delete',
        url: '/message/delete/batch',
        data:{
            "ids": ids
        }
    })
}