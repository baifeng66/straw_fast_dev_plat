// 引入 `defineStore()` 方法
import { defineStore } from 'pinia'

// 你可以任意命名 `defineStore()` 的返回值，但最好使用 store 的名字，同时以 `use` 开头且以 `Store` 结尾。
// (比如 `useUserStore`，`useCartStore`，`useProductStore`)
// 第一个参数是你的应用中 Store 的唯一 ID。
export const useMenuStore = defineStore('menu', {
    // 定义状态【数据】
    state: () => ({
        menuList: [],
    }),
    // 获取数据的方法
    getters: {
        Array: (state) => state.menuList,
    },
    // 修改数据的方法
    actions: {
        setMenuList(data) {
            console.log("setMenuList==========>", data);
            this.menuList = data;
        }
    },

})