package cn.fuser.amap


import cn.fuser.amap.net.HttpParam
import cn.fuser.amap.net.HttpRequest
import cn.fuser.amap.net.RequestParser
import okhttp3.HttpUrl
import okhttp3.Request
import org.apache.commons.codec.digest.DigestUtils
import java.util.*
import kotlin.reflect.full.findAnnotation

fun StringJoiner.addAll(list: Collection<String>): StringJoiner {
    list.forEach { this.add(it) }
    return this
}

class AMapRequestParser : RequestParser<AMapRequest> {
    override fun parse(req: AMapRequest): Request = req.request()
}

abstract class AMapRequest {
    fun request(): Request {
        val clz = this::class
        val rt = clz.findAnnotation<HttpRequest>() ?: throw IllegalAccessException()
        val ub = HttpUrl.parse(rt.url)?.newBuilder() ?: throw IllegalArgumentException()
        val mapping = HashMap<String, String>()
        this::class.members.forEach {
            val tag = it.findAnnotation<HttpParam>() ?: return@forEach
            val value = it.call(this)?.toString() ?: ""
            mapping[tag.name] = value
            ub.addQueryParameter(tag.name, value)
        }
        val lk = ArrayList<String>(mapping.keys)
        lk.sort()
        val data = StringJoiner("&").addAll(lk.map { "%s=%s".format(it, mapping[it]) }).toString()
        val sign = DigestUtils.md5Hex(data)
        ub.addQueryParameter("sig", sign)
        return Request.Builder().url(ub.build()).build()
    }
}

@HttpRequest(url = "https://restapi.amap.com/v3/ip?parameters")
class IPLocation(address: String) : AMapRequest() {
    @HttpParam(name = "key")
    val key: String = Config.key
    @HttpParam(name = "ip")
    val ip: String = address
    @HttpParam(name = "output")
    val output: String = "JSON"
}

@HttpRequest(url = "https://yuntuapi.amap.com/nearby/around")
class NearBy(radius: Int, limit: Int, lon: Float, lat: Float) : AMapRequest() {
    @HttpParam(name = "key")
    val key: String = Config.key
    @HttpParam(name = "radius")
    val radius: Int = radius
    @HttpParam(name = "limit")
    val limit: Int = limit
    @HttpParam(name = "center")
    val center: String = "%f,%f".format(lon, lat)
}

@HttpRequest(url = "https://restapi.amap.com/v3/place/text")
class Search(kw: String, city: String, cityLimit: Boolean) : AMapRequest() {
    @HttpParam(name = "key")
    val key: String = Config.key
    @HttpParam(name = "city")
    val city: String = city
    @HttpParam(name = "keywords")
    val keywords: String = kw
    @HttpParam(name = "citylimit")
    val cityLimit: Boolean = cityLimit
}