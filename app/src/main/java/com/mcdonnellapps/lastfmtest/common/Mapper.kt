package com.mcdonnellapps.lastfmtest.common

abstract class Mapper<Input, Output> {

    abstract fun map(input: Input): Output

    open fun mapList(input: List<Input>): List<Output> {
        return input.map { map(it) }
    }

}