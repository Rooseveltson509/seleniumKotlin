package com.qaautoman.Enum

enum class Device(val width: Int, val length: Int, val isReal: Boolean) {
    WINDOWS(-1, -1, true), IPHONE(1, 1, false);
}
