import request from '@/utils/request'

export function QueryCodeInfo(codeInfoQueryVO) {
    return request({
        url: '/codeinfo/queryCodeInfo',
        method: 'post',
        data: codeInfoQueryVO
    })
}


