package com.hwq.ruminate.concurrent.KtCoroutines

import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

object CorontinesCenter {
    val time = SimpleDateFormat("hh:MM:ss")

    @JvmStatic
    fun main(args: Array<String>) {
        launchTest()

        //runBlock()
    }

    fun launchTest() {
        GlobalScope.launch {
            delay(1000)
            println("${time.format(Date())}  World! ${Thread.currentThread().name + " " + Thread.currentThread().id}")
        }
        GlobalScope.launch {
            delay(1000)
            println("${time.format(Date())}  World2! ${Thread.currentThread().name + " " + Thread.currentThread().id}")
        }
        GlobalScope.launch {
            delay(1000)
            println("${time.format(Date())}  World3! ${Thread.currentThread().name + " " + Thread.currentThread().id}")
        }
        println("${time.format(Date())}  Hello, ${Thread.currentThread().name + " " + Thread.currentThread().id}")
        Thread.sleep(3000)
        println("${time.format(Date())}  End. ${Thread.currentThread().name + " " + Thread.currentThread().id}")
    }

    fun runBlock() {

        val launch = GlobalScope.launch {
            repeat(100) {
                println("${time.format(Date())} hello $it")
            }
        }

        runBlocking {
            println("${time.format(Date())}  wait.")
            launch.join()
        }

        println("END")
    }
}