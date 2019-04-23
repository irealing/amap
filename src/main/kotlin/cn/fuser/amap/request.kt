package cn.fuser.amap


import cn.fuser.amap.net.HttpParam
import cn.fuser.amap.net.HttpRequest
import org.apache.commons.codec.digest.DigestUtils
import java.util.*
import kotlin.reflect.full.findAnnotation

fun StringJoiner.addAll(list: Collection<String>): StringJoiner {
    list.forEach { this.add(it) }
    return this
}

enum class SortRule(private val rule: String) {
    DISTANCE("distance"), WEIGHT("weight");

    override fun toString(): String {
        return rule
    }
}

enum class OutputType(private val value: String) {
    JSON("json"), XML("xml");

    override fun toString(): String {
        return value
    }
}

abstract class AMapRequest {
    @HttpParam(name = "key")
    val key: String = Config.key

    @HttpParam("sig")
    fun sign(): String {
        val clz = this::class
        val rt = clz.findAnnotation<HttpRequest>() ?: throw IllegalAccessException()
        val mapping = HashMap<String, String>()
        this::class.members.forEach {
            val tag = it.findAnnotation<HttpParam>() ?: return@forEach
            if (tag.name == "sig") return@forEach
            val value = it.call(this)?.toString() ?: ""
            mapping[tag.name] = value
        }
        val lk = ArrayList<String>(mapping.keys)
        lk.sort()
        val data = StringJoiner("&").addAll(lk.map { "%s=%s".format(it, mapping[it]) }).toString()
        return DigestUtils.md5Hex(data)
    }
}

@HttpRequest(url = "https://restapi.amap.com/v3/ip?parameters")
class IPLocationRequest(address: String) : AMapRequest() {
    @HttpParam(name = "ip")
    val ip: String = address
    @HttpParam(name = "output")
    val output: String = "JSON"
}

@HttpRequest(url = "https://yuntuapi.amap.com/nearby/around")
class NearBy(radius: Int, limit: Int, lon: Float, lat: Float) : AMapRequest() {
    @HttpParam(name = "radius")
    val radius: Int = radius
    @HttpParam(name = "limit")
    val limit: Int = limit
    @HttpParam(name = "center")
    val center: String = "%f,%f".format(lon, lat)
}

@HttpRequest(url = "https://restapi.amap.com/v3/place/text")
class Search(kw: String, city: String, cityLimit: Boolean) : AMapRequest() {
    @HttpParam(name = "city")
    val city: String = city
    @HttpParam(name = "keywords")
    val keywords: String = kw
    @HttpParam(name = "citylimit")
    val cityLimit: Boolean = cityLimit
}

@HttpRequest(url = "https://restapi.amap.com/v3/place/around")
class AroundSearch(lon: Float, lat: Float, kw: String, city: String?, radius: Int) : AMapRequest() {
    @HttpParam(name = "location")
    val location: String = "%s,%s".format(lon, lat)
    @HttpParam(name = "keywords")
    val keywords: String = kw
    @HttpParam(name = "city")
    val city: String = city ?: ""
    @HttpParam(name = "radius")
    var radius: Int = radius
    @HttpParam(name = "sortrule")
    var sortRule: SortRule = SortRule.DISTANCE
    @HttpParam(name = "page")
    var page: Int = 1
    @HttpParam(name = "offset")
    var offset: Int = 20
}
