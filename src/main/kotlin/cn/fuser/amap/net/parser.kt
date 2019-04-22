package cn.fuser.amap.net

import com.alibaba.fastjson.JSON
import okhttp3.*
import java.lang.Exception
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/***
 * RequestParser
 * convert object to HTTP Request
 * @see Request
 * */
interface RequestParser<T> {
    fun parse(req: T): Request
}

/***
 * ResponseParser
 * convert Response to object
 * @see Response
 * */
interface ResponseParser<T> {
    fun parse(resp: Response): T
}

class HttpRequestParser : RequestParser<Any> {
    override fun parse(req: Any): Request = makeRequest(req)
    private fun makeRequest(obj: Any): Request {
        val clz = obj::class
        val tag = clz.findAnnotation<HttpRequest>() ?: throw IllegalArgumentException()
        if (tag.method == HttpMethod.GET) return makeGet(tag.url, obj)
        return Request.Builder().url(tag.url).method(tag.method.method, formBody(clz, obj)).build()
    }

    private fun formBody(clz: KClass<*>, obj: Any): FormBody {
        val fb = FormBody.Builder()
        clz.members.forEach {
            val pt = it.findAnnotation<HttpParam>()?.name ?: return@forEach
            val value = it.call(obj)?.toString() ?: ""
            fb.add(pt, value)
        }
        return fb.build()
    }

    private fun makeGet(url: String, obj: Any): Request {
        val builder = HttpUrl.parse(url)!!.newBuilder()
        for (member in obj::class.members) {
            val pt = member.findAnnotation<HttpParam>()?.name ?: continue
            val arg = member.call(obj)?.toString() ?: ""
            builder.addQueryParameter(pt, arg)
        }
        return Request.Builder().url(builder.build()).get().build()
    }

    companion object {
        val instance = HttpRequestParser()
    }
}

class JSONRequestParser : RequestParser<Any> {
    companion object {
        val instance = JSONRequestParser()
    }

    override fun parse(req: Any): Request {
        val clz = req::class
        val tag = clz.findAnnotation<HttpRequest>() ?: throw IllegalArgumentException()
        val rb = Request.Builder().url(tag.url)
        val body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), JSON.toJSONBytes(req))
        return rb.method(tag.method.method, body).build()
    }
}

abstract class BaseResponseParser<T>(private val validateCode: Boolean) : ResponseParser<T> {
    override fun parse(resp: Response): T {
        if (validateCode && resp.code() != 200)
            throw Exception()
        return this.render(resp.body())
    }

    abstract fun render(body: ResponseBody?): T
}

class TextResponseParser : ResponseParser<String?> {
    companion object {
        val instance = TextResponseParser()
    }

    override fun parse(resp: Response): String? {
        val ret = resp.body()?.bytes() ?: return null
        return String(ret)
    }
}

class BytesResponseParser<T>(private val func: (ByteArray?) -> T) : ResponseParser<T> {
    override fun parse(resp: Response): T = func.invoke(resp.body()?.bytes())
}
