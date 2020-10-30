import java.io.File

/**
 * @author Mohammad Amin Dadgar
 *
 */

fun main(args:Array<String>){

    val initPopulation = InitPopulation()
    initPopulation.getIndividualsFromMatrixFile(File("src/main/resources/dataset.txt"))

    println(initPopulation.graphAdjadancyMatrix[0])

}
