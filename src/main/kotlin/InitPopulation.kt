import java.io.File

class InitPopulation {


    /**
     * @param graphAdjadancyMatrix is for dataset
     *
     */
    val graphAdjadancyMatrix:ArrayList<ArrayList<Int>> = arrayListOf(arrayListOf())

    fun getIndividualsFromMatrixFile(file:File){
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
    }

}