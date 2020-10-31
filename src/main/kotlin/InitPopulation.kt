import java.io.File

class InitPopulation(private val file: File,
                     private val individualCount:Int=30,
                     private val enableLog:Boolean=true) {

    val graphAdjadancyMatrix:ArrayList<ArrayList<Int>> = arrayListOf(arrayListOf())
    val Individuals:ArrayList<ArrayList<Int>> = arrayListOf(arrayListOf())
    val fitness:ArrayList<Int> = arrayListOf()


    /**
     * @param individualCount is our individuals count, Default is 30
     * @param graphAdjadancyMatrix is for dataset
     * @param Individuals is our individuals
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


    private fun printline(s:Any){
        if (enableLog)
            println(s)
    }

}