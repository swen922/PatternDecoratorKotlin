
interface DataSource {
    fun writeData(data : String)
    fun readData() : String
}

class FileDataSource : DataSource {

    private var myData = ""

    override fun writeData(data: String) {
        myData = data
        println("write data to file : ${data}")
    }

    override fun readData(): String {
        return "read data from file: ${myData}"
    }
}

class CompressionDecorator : DataSource {

    private var mySource : DataSource

    constructor(source: DataSource) {
        this.mySource = source
    }

    override fun writeData(data: String) {
        val compressedData = "COMPRESSED this data: ${data}"
        mySource.writeData(compressedData)
    }

    override fun readData(): String {
        val decompressedData = "DECOMPRESSED this data: " + mySource.readData()
        return decompressedData
    }
}


class EncryprionDecorator : DataSource {

    private var mySource : DataSource

    constructor(source: DataSource) {
        this.mySource = source
    }

    override fun writeData(data: String) {
        val encryptedData = "ECRYPTED this data: ${data}"
        mySource.writeData(encryptedData)
    }

    override fun readData(): String {
        val decryptedData = "DECRYPTED this data: " + mySource.readData()
        return decryptedData
    }
}


/** Декоратор, также известен как: Wrapper, Обёртка, Decorator */


fun main(args: Array<String>) {
    val fileSource = FileDataSource()
    val compDecor = CompressionDecorator(fileSource)
    var encDecor = EncryprionDecorator(compDecor)

    compDecor.writeData("big secrete!")
    println(compDecor.readData())

    encDecor.writeData("very big secret")
    println(encDecor.readData())

}