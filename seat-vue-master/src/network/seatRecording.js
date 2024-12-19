import {request} from "./request";

export function getData() {
    return request({
        method: 'post',
        url: '/class-user/list'
    })
}

export function cancel(id) {
    return request({
        method: 'get',
        url: '/class-user/cancel/'+id
    })
}
