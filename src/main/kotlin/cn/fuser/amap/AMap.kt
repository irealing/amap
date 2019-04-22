package cn.fuser.amap

import cn.fuser.amap.net.NetLoader
import cn.fuser.amap.net.TextResponseParser

class AMap {
    fun ipLocation(ip: String): String? {
        return NetLoader.request(IPLocation(ip), TextResponseParser.instance)
    }
}