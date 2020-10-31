import DataObject.fitness
import DataObject.fitnessPercentage
import kotlin.math.roundToInt


class RouletteWheel {

    init {
        computeIndividualsPercentage()
    }
    private fun computeIndividualsPercentage(){
        var totalFitness = 0f
        fitness.forEach {
            totalFitness += it
        }

        // scale value is to compute individuals possibilities
        val scaleValue = (1 / totalFitness)
        println("precisionValue $scaleValue")

        for (i in 0 until fitness.size){
            // make every individual percentage and then round it with 2 decimals
            fitnessPercentage.add(i,((fitness[i] * scaleValue * 10000).roundToInt().toFloat())/100)
        }
    }
}