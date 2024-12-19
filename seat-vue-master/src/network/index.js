import {request} from "./request";

export function getNotices() {
    return request({
        method: 'post',
        url: '/notice/get/list',
        data: {
            "currentPage": 1,
            "pageSize": 10,
            "title": ""
        }
    })
}

export function getClass() {
    return request({
        method: 'put',
        url: '/admin/class/find/list',
        data: {
            "currentPage": 1,
            "pageSize": 10,
            "title": ""
        }
    })
}

export function getClassById(id) {
    return request({
        method: 'get',
        url: '/seat/get/list/' + id
    })
}

export function checkedSeat(data) {
    return request({
        method: 'post',
        url: '/class-user/check',
        data: {
            "classId": data.cid,
            "seatNum": data.sid
        }
    })
}