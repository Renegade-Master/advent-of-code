package com.renegademaster.aoc.day1

import mu.KotlinLogging
import java.io.BufferedReader
import kotlin.system.exitProcess

private val logger = KotlinLogging.logger {}

fun getHighestTripleGroupTotal(calorieList: ArrayList<ArrayList<Int>>): List<Int> {
    val topThreeGroupTotals: ArrayList<Int> = ArrayList()
    var groupTotal: Int

    logger.info { "Computing highest group total..." }

    calorieList.forEach { group ->
        groupTotal = 0

        // Add up the current group
        group.forEach {
            logger.debug("Adding [$it] to [$groupTotal]")
            groupTotal += it
        }

        if (topThreeGroupTotals.size < 3) {
            topThreeGroupTotals.add(groupTotal)
            return@forEach
        }

        topThreeGroupTotals.sort()

        // Check if this group has a higher total
        if (groupTotal > topThreeGroupTotals[0]) {
            logger.debug("[$groupTotal] is higher than [${topThreeGroupTotals[0]}].")

            if (groupTotal > topThreeGroupTotals[1]) {
                logger.debug("[$groupTotal] is higher than [${topThreeGroupTotals[1]}].")

                if (groupTotal > topThreeGroupTotals[2]) {
                    logger.debug("[$groupTotal] is higher than [${topThreeGroupTotals[2]}]. Setting new highest value.")
                    topThreeGroupTotals[0] = topThreeGroupTotals[1]
                    topThreeGroupTotals[1] = topThreeGroupTotals[2]
                    topThreeGroupTotals[2] = groupTotal
                    return@forEach
                }

                logger.debug("Setting new highest value.")
                topThreeGroupTotals[0] = topThreeGroupTotals[1]
                topThreeGroupTotals[1] = groupTotal
                return@forEach
            }

            logger.debug("Setting new highest value.")
            topThreeGroupTotals[0] = groupTotal
            return@forEach
        } else {
            logger.debug("[$groupTotal] is not higher than [${topThreeGroupTotals}]")
        }
    }

    logger.info { "Determined highest group total." }

    return topThreeGroupTotals
}


fun getHighestGroupTotal(calorieList: ArrayList<ArrayList<Int>>): Int {
    var highestTotalCalories: Int = -1
    var groupTotal: Int

    logger.info { "Computing highest group total..." }

    calorieList.forEach { group ->
        groupTotal = 0

        // Add up the current group
        group.forEach {
            logger.debug("Adding [$it] to [$groupTotal]")
            groupTotal += it
        }

        // Check if this group has a higher total
        if (groupTotal > highestTotalCalories) {
            logger.debug("[$groupTotal] is higher than [$highestTotalCalories]. Setting new highest value.")
            highestTotalCalories = groupTotal
        } else {
            logger.debug("[$groupTotal] is not higher than [$highestTotalCalories]")
        }
    }

    logger.info { "Determined highest group total." }

    return highestTotalCalories
}

fun getCalorieList(data: BufferedReader): ArrayList<ArrayList<Int>> {
    val calorieList: ArrayList<ArrayList<Int>> = ArrayList()
    val group: ArrayList<Int> = ArrayList()

    logger.info { "Parsing calorie list from file content..." }

    // Open the input file for reading
    data.use { reader ->

        reader.readLines().forEach { line ->
            if (line == "") {
                logger.debug("Blank Line!")

                calorieList.add(ArrayList(group))
                group.clear()
            } else {
                logger.debug("Content Line! [$line]")
                group.add(line.toInt())
            }
        }
    }

    // Add the final group
    calorieList.add(group)

    logger.info { "Calorie list parsed." }

    return calorieList
}


fun getInputFile(): BufferedReader {
    logger.info { "Reading file from Resources..." }
    val fileStream = {}.javaClass.getResourceAsStream("/day_1/input.txt")

    if (fileStream == null) {
        logger.error { "File could not be read. Exiting." }
        exitProcess(1)
    }

    logger.info { "File read from Resources." }

    return fileStream.bufferedReader()
}


fun main(args: Array<String>) {
    println("Advent of Code Challenge - Day 1!")

    val data = getInputFile()

    val calorieList = getCalorieList(data)

    val highestGroupTotal = getHighestGroupTotal(calorieList)
    println("Total calories of the group with the highest combined calories: [$highestGroupTotal]")

    val highestTripleGroupTotal = getHighestTripleGroupTotal(calorieList)
    println("Top 3 groups with the highest combined calories: [${highestTripleGroupTotal}]")
    println("Total calories of the top 3 groups with the highest combined calories: [${highestTripleGroupTotal.sum()}]")
}
