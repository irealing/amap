package cn.fuser.amap

import cn.fuser.util.Property

private const val defaultConfigFile = "amap.properties"

object Config {
    private val prop = Property.create(defaultConfigFile)
    val key: String by prop.string("amap.key")
}