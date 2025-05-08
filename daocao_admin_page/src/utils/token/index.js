
// 存储token到sessionStorage中
export function setToken(tokenKey, tokenValue) {
    // setItem：设置由密钥到值识别的对的值，如果以前不存在密钥，则创建一个新的键/值对。
    return sessionStorage.setItem(tokenKey, tokenValue)
}

// 获取token值
export function getToken(tokenKey) {
    // getItem：返回与给定密钥关联的当前值，如果给定密钥不存在，则返回null。
    return sessionStorage.getItem(tokenKey)
}

// 删除token值
export function removeToken(tokenKey) {
    if (getToken(tokenKey)) {
        // removeItem：如果存在与给定密钥的密钥/值对，则将键/值对删除
        return sessionStorage.removeItem(tokenKey)
    }
    return null
}
