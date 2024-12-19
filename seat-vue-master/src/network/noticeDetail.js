import {request} from "./request";

export function getNoticeDetail(id) {
    return request({
        method: 'get',
        url: '/notice/get/one/'+ id
    })
}