import request from "@/utils/request";

export function searchSelfMenu() {
    return request({
        url: 'menu/self',
        method: 'get'
    })
}