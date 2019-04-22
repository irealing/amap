package cn.fuser.amap

import cn.fuser.amap.net.NetLoader
import cn.fuser.amap.net.BytesResponseParser
import com.alibaba.fastjson.JSON

class AMap {
    fun ipLocation(ip: String): IPLocation {
        return NetLoader.request(IPLocationRequest(ip), BytesResponseParser {
            val data = if (it != null) String(it) else throw IllegalArgumentException("empty response body")
            JSON.parseObject(data, IPLocation::class.java)
        })
    }
}