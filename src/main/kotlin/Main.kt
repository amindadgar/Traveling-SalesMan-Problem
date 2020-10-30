import java.io.File


fun main(args:Array<String>){

    File("src/main/resources/dataset.txt").forEachLine {
        println(it)
    }

}
