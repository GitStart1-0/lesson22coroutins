package com.example.lesson22coroutines

import kotlinx.coroutines.delay

object Coroutines {

	private suspend fun firstWork(): String {
		delay(1000)
		return "Work 1 finished"
	}
	private suspend fun secondWork(): String {
		delay(1000)
		return "Work 2 finished"
	}
	private suspend fun thirdWork(): String {
		delay(1000)
		return "Work 3 finished"
	}
	private suspend fun fourthWork(): String {
		delay(1000)
		return "Work 4 finished"
	}

	suspend fun doWork():String {
		val firstWorkResult = firstWork()
		val secondWorkResult = secondWork()
		val thirdWorkResult = thirdWork()
		val fourthWorkResult = fourthWork()
		return "$firstWorkResult $secondWorkResult $thirdWorkResult $fourthWorkResult"
	}

}