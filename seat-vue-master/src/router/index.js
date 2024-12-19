import Vue from 'vue'
import VueRouter from "vue-router";

const Home = () => import('../views/admin/home/Home')
const Login = () => import('../views/Login')
const Register = () => import('../views/Register')
const AdminLog = () => import('../views/admin/AdminLog')
const AdminClass = () => import('../views/admin/AdminClass')
const AdminNotice = () => import('../views/admin/AdminNotice')
const AdminUser = () => import('../views/admin/AdminUser')
const UserIndex = () => import('../views/user/Index')
const NoticeDetail = () => import('../views/user/NoticeDetail')
const CheckClass = () => import('../views/user/CheckClass')
const SeatRecording = () => import('../views/user/SeatRecording')
const Reply = () => import('../views/user/Reply')
const SeatFx = () => import('../views/admin/seatfx')

Vue.use(VueRouter)

const routes = [
    {
        path: '/admin',
        component: Home,
        children: [
            {
                path: '/adminLog',
                component: AdminLog
            },
            {
                path: '/adminNotice',
                component: AdminNotice
            },
            {
                path: '/adminUser',
                component: AdminUser
            },
            {
                path: '/adminClass',
                component: AdminClass
            },
            {
                path: '/seatfx',
                component: SeatFx
            }
        ]
    },
    {
        path: '/login',
        component: Login
    }, {
        path: '/register',
        component: Register
    },
    {
        path: '/',
        component: UserIndex
    },
    {
        path: '/noticeDetail/:id',
        component: NoticeDetail
    },
    {
        path: '/checkClass/:id',
        component: CheckClass
    },
    {
        path: '/seatRecording',
        component: SeatRecording
    },
    {
        path: '/reply',
        component: Reply
    }
]

const router = new VueRouter({
    routes,
    mode: 'history'
})

export default router
