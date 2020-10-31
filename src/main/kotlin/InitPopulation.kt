import DataObject.Individuals
import DataObject.fitness
import DataObject.graphAdjadancyMatrix
import java.io.File
import kotlin.math.roundToInt

class InitPopulation(private val file: File,
                     private val individualCount:Int=30,
                     private val enableLog:Boolean=true) {



    /**
     * @param individualCount is our individuals count, Default is 30
     * @param enableLog is to enable or disable log
     *
     * At first getIndividualsFromMatrixFile() will getData from dataset
     * then we will create individual and check if there is a path between them
     *
     */



    init {
        getIndividualsFromMatrixFile()

        //Create Population
        for (i in 0 until individualCount){
            var individual = addIndividual()
            printline("individual No.${i+1}:  $individual")

            //LOOP: check if there was a path, else try to get another individual with a path
            while (checkIndividualPath(individual)==null) {
                printline("individual No.${i+1} is not having a path\nSo trying to get a new individual instead")
                individual = addIndividual()
                printline("New individual No.${i+1}:  $individual")
            }

            Individuals.add(i,individual)
        }
        EvaluateFittness(Individuals)
    }


    private fun getIndividualsFromMatrixFile(){
        /**
         * get data form dataset.txt file and save it into graphAdjadancyMatrix variable
          */
        var i = 0
        file.forEachLine { line ->
            val s = line.split(" ")
            val LineInt = arrayListOf<Int>()
            s.forEach {
                LineInt.add( it.toInt())
            }
            graphAdjadancyMatrix.add(i,LineInt)
            i++
        }
        printline(graphAdjadancyMatrix.size)
    }

    private fun addIndividual():ArrayList<Int>{
        // first of all create a random array with numbers between 0 and 25 with no repetition
        val individual = arrayListOf<Int>()
        for (i in 0..25){
            var randomNumber = (0..25).random()

            while (individual.contains(randomNumber)){
                // while the random number is duplicate try to get another number
                randomNumber = (0..25).random()
            }
            individual.add(i,randomNumber)
        }
        // at last an individual is made
        return individual

    }

    private fun checkIndividualPath(individual:ArrayList<Int>):ArrayList<Int>?{
        var i = 0
        while(i<25){
            val index0 = individual[i]
            val indext1 = individual[i+1]
            if (graphAdjadancyMatrix[index0][indext1] == 0) {
                // if there was no path, return null !!
                return null
            }
            printline("$i ${i+1} path checked!")
            i++
        }
        return individual
    }

    private fun EvaluateFittness(individuals: ArrayList<ArrayList<Int>>){

        // for all individuals
        for (i in 0 until individualCount){
            // make add an index to array, index is i
            fitness.add(i,0f)

            // check each fitness
            for (j in 0 until 25){
                val index0 =  individuals[i][j]
                val index1 = individuals[i][j+1]
                fitness[i] = fitness[i] + graphAdjadancyMatrix[index0][index1]
            }
            fitness[i] = (((1 / fitness[i]) *1000000).roundToInt().toFloat())/1000

            printline("Fitness No.${i+1}  ${( (fitness[i]) )}")
        }
    }


    private fun printline(s:Any){
        if (enableLog)
            println(s)
    }

}