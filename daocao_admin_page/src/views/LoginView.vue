<template>
    <!-- 根 -->
    <div class="login_container">
        <!-- 右侧 -->
        <div class="login_form">
            <h3>稻草快速开发平台</h3>
            <el-form ref="loginFormRef" :model="login_form" :rules="rules">
                <!-- 用户名 -->
                <el-form-item prop="account">
                    <el-input v-model="login_form.account" placeholder="请输入账号">
                        <template #prefix>
                            <el-icon class="el-input__icon">
                                <user />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <!-- 密码 -->
                <el-form-item prop="password">
                    <el-input type="password" v-model="login_form.password" placeholder="请输入密码">
                        <template #prefix>
                            <el-icon class="el-input__icon">
                                <lock />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <!-- 记住我和忘记密码 -->
                <div class="rememberMe">
                    <el-checkbox v-model="login_form.rememberMe" label="记住我" size="large" />
                    <el-text class="mx-1 forgetPwd" type="primary">忘记密码</el-text>
                </div>
                <!-- 分隔符 -->
                <el-divider content-position="center">其他登录方式</el-divider>
                <!-- 其它登录方式 -->
                <div class="other-login">
                    <el-icon class="other-login-item">
                        <ChromeFilled />
                    </el-icon>
                    <el-icon class="other-login-item">
                        <ElemeFilled />
                    </el-icon>
                    <el-icon class="other-login-item">
                        <WindPower />
                    </el-icon>
                </div>
                <!-- 按钮 -->
                <el-form-item>
                    <el-button class="login_btn" style="width: 100%;" type="primary" @click="handleLogin">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
// 导入 api
import { login } from '@/api/auth/index.js'
import { searchSelfMenu } from '@/api/menu/index.js'
// 导入 token 存储方法
import { setToken } from '@/utils/token/index.js'
// 导入 pinia 状态管理
import { useMenuStore } from '@/stores/index.js'
let menuStore = useMenuStore()

const loginFormRef = ref()
// 声明表单数据
const login_form = ref({
    account: undefined,
    password: undefined,
    rememberMe: undefined,
})
// 表单验证规则
const rules = ref({
    account: [
        { required: true, message: 'Please input account', trigger: 'blur' },
        { min: 2, max: 5, message: 'Length should be 2 to 5', trigger: 'blur' },
    ],
    password: [
        { required: true, message: 'Please input password', trigger: 'blur' },
        { min: 6, max: 10, message: 'Length should be 6 to 10', trigger: 'blur' },
    ],
})
// 定义登录方法
function handleLogin() {
    login(login_form.value).then(res => {
        console.log(res)
        if (res.data.code == 200) {
            // 存储 token 到 pinia
            console.log('登录成功');
            setToken("daocao-token", res.data.token)
            //todo 查询用户权限和菜单【实现动态路由】
            searchSelfMenu().then(res => {
                console.log('res ==>', res)
                if (res.data.code == 200) {
                    menuStore.setMenuList(res.data.data)
                }
                // 跳转到首页
                // router.push('/')
            })
        }
    })
}
</script>

<style lang="scss" scoped>
.login_container {
    background-image: url('@/assets/bg1.png');
    background-size: 100%;
    height: 100vh;
    display: flex;
    justify-content: flex-end;

    .login_form {
        h3 {
            margin-bottom: 20px;
        }

        display: flex;
        justify-content: center;
        align-items: center;
        // 换行
        flex-direction: column;
        width: 50%;
        background-color: #fff;

        .rememberMe {
            display: flex;
            justify-content: space-between;
            align-items: center;

            .forgetPwd {
                cursor: pointer;
            }
        }

        // 其它登录
        .other-login {
            display: flex;
            justify-content: center;

            .other-login-item {
                margin-right: 60px;
                cursor: pointer;
            }
        }

        // 登录按钮
        .login_btn {
            margin-top: 20px;
        }
    }
}

// 设置 form 的宽度
.el-form {
    width: 60%;
}

.el-form-item {
    width: 100%;
}
</style>