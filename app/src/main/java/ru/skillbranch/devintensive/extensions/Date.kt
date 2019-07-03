package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()): String {

    var different = date.time - this.time
    val delta = different

    /*System.out.println("startDate : $date")
    System.out.println("endDate : $this")
    System.out.println("different : $different")*/

    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24

    val elapsedDays: Int = (different / daysInMilli).toInt()
    different %= daysInMilli

    val elapsedHours: Int = (different / hoursInMilli).toInt()
    different %= hoursInMilli

    val elapsedMinutes: Int = (different / minutesInMilli).toInt()
    different %= minutesInMilli

    //val elapsedSeconds: Int = (different / secondsInMilli).toInt()

    /*val printf = System.out.printf(
            "%d days, %d hours, %d minutes, %d seconds%n",
            elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds)*/

    if(delta  in 0 until SECOND){
        return "только что"
    } else if (delta  in SECOND until 45*SECOND){
        return "несколько секунд назад"
    } else if (delta in 45*SECOND until 75*SECOND){
        return "минуту назад"
    } else if (delta in 75*SECOND until 45*MINUTE){
        if(elapsedMinutes.equals(1)){
            return "$elapsedMinutes минуту назад"
        } else if (elapsedMinutes in 2..4){
            return "$elapsedMinutes минуты назад"
        } else if (elapsedMinutes in 5..20){
            return "$elapsedMinutes минут назад"
        } else if ((elapsedMinutes % 10) == 1){
            return "$elapsedMinutes минуту назад"
        } else if ((elapsedMinutes % 10) in 2..4){
            return "$elapsedMinutes минуты назад"
        } else
            return "$elapsedMinutes минут назад"
    } else if (delta in 45*MINUTE until 75*MINUTE){
        return "час назад"
    } else if (delta in 75*MINUTE until 22*HOUR){
        if(elapsedHours.equals(1)){
            return "$elapsedHours час назад"
        } else if (elapsedHours in 2..4){
            return "$elapsedHours часа назад"
        } else if (elapsedHours in 5..20){
            return "$elapsedHours часов назад"
        } else if ((elapsedHours % 10) == 1){
            return "$elapsedHours час назад"
        } else if ((elapsedHours % 10) in 2..4){
            return "$elapsedHours часа назад"
        } else
            return "$elapsedHours часов назад"
    } else if (delta in 22*HOUR until 26*HOUR){
        return "день назад"
    } else if (delta in 26*HOUR until 360*DAY){
        if(elapsedDays.equals(1)){
            return "$elapsedDays день назад"
        } else if (elapsedDays in 2..4){
            return "$elapsedDays дня назад"
        } else if (elapsedDays in 5..20){
            return "$elapsedDays дней назад"
        } else if ((elapsedDays % 10) == 1){
            return "$elapsedDays день назад"
        } else if ((elapsedDays % 10) in 2..4){
            return "$elapsedDays дня назад"
        } else
            return "$elapsedDays дней назад"
    } else if (delta >= 360*DAY) {
        return "более года назад"
    }

    return "error"
}


enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}