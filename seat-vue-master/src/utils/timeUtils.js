export function timestampToTime(time) {// 时间戳为10位需*1000，时间戳为13位的话不需乘1000

    const d = new Date(time)
    return d.toLocaleDateString().replace(/\//g, "-") + " " + d.toTimeString().substr(0, 8)
}