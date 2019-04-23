package cn.fuser.amap

import cn.fuser.amap.net.NetLoader
import cn.fuser.amap.net.BytesResponseParser
import com.alibaba.fastjson.JSON

class AMap {
    private inline fun <reified T : AMapRequest, reified R> request(t: T): R {
        return NetLoader.request(t, BytesResponseParser {
            val data = if (it != null) String(it) else throw IllegalArgumentException("empty response body")
            JSON.parseObject(data, R::class.java)
        })
    }

    fun ipLocation(ip: String): IPLocation = request(IPLocationRequest(ip))

    fun search(s: AroundSearch): SearchRet = request(s)
    fun search(s: Search): SearchRet = request(s)
}