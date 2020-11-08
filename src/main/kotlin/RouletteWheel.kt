import DataObject.fitness
import DataObject.fitnessPercentage
import kotlin.math.roundToInt


class RouletteWheel {

    init {
        computeIndividualsPercentage()
        rouletteWheel(fitnessPercentage)
    }
    private fun computeIndividualsPercentage(){
        var totalFitness = 0f
        fitness.forEach {
            totalFitness += it
        }

        // scale value is to compute individuals possibilities
        val scaleValue = (1 / totalFitness)
        println("scaleValue $scaleValue")

        for (i in 0 until fitness.size){
            // make every individual percentage and then round it with 2 decimals
            fitnessPercentage.add(i,((fitness[i] * scaleValue * 10000).roundToInt().toFloat())/100)
        }
        // Just print each individual fitness Percentage
        for (i in 0 until fitnessPercentage.size){
            println("Fitness No.$i percentage  ${fitnessPercentage[i]}")
        }
    }

    // this function will return individual number
    private fun rouletteWheel(fitnessPercentage:ArrayList<Float>):Int{
        val dataArray = arrayListOf<Int>()
        // insert every individual into dataArray with count of their percentage*100
        // index refers to individual number
        for (index in 0 until fitnessPercentage.size){
            val repeatCount:Int = (fitnessPercentage[index] * 100).toInt()
            for (i in 0..repeatCount){
                dataArray.add(index)
            }
        }
        val randomIndividual = (0..dataArray.size).random()

        println("Selected individual  ${dataArray[randomIndividual]}")
        return dataArray[randomIndividual]

    }


}