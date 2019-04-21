package cn.fuser.amap.net

enum class HttpMethod(val method: String) {
    GET("GET"), POST("POST"), PUT("PUT"), PATCH("PATCH")
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HttpRequest(val url: String, val method: HttpMethod = HttpMethod.GET)

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class HttpParam(val name: String)
