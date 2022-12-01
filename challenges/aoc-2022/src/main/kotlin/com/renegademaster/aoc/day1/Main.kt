package com.renegademaster.aoc.day1

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun getInputFile(): Unit {
    logger.info { "Reading file from Resources..." }
    val fileStream = {}.javaClass.getResourceAsStream("day_1/input.txt")
    logger.info { "File read from Resources." }

    // Open the input file for reading
    fileStream?.bufferedReader().use { reader ->
        reader?.readLines()?.forEach { line ->
            if(line == "") {
                println("Blank Line!")
            }
        }
    }
}

fun main(args: Array<String>) {
    println("Advent of Code Challenge - Day 1!")

    getInputFile()
}
