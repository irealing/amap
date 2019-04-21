package cn.fuser.amap

import cn.fuser.util.Property

object Config {
    private val prop = Property.create("config.properties")
    val key:String by prop.string("amap.key")
}