package cn.fuser.amap.net

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.logging.Level
import java.util.logging.Logger

object NetLoader {
    private val client = OkHttpClient()
    private val logger = Logger.getLogger(this::class.simpleName)
    private fun request(req: Request): Response {
        val ret = client.newCall(req).execute()
        logger.log(Level.ALL, "request url %s %d".format(req.url().toString(), ret.code()))
        return ret
    }

    @SuppressWarnings("unused")
    fun <T> request(req: T, parser: RequestParser<T>): Response = request(parser.parse(req))

    fun request(obj: Any): Response = request(obj, HttpRequestParser.instance)
    fun <T, P> request(req: T, parser: RequestParser<T>, respParser: ResponseParser<P>) = respParser.parse(request(req, parser))
    fun <T> request(obj: Any, respParser: ResponseParser<T>): T = respParser.parse(request(obj))
    fun json(obj: Any): Response = client.newCall(JSONRequestParser.instance.parse(obj)).execute()
    fun <T> json(obj: Any, respParser: ResponseParser<T>): T = respParser.parse(json(obj))
}