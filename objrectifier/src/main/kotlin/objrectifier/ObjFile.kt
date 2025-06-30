package objrectifier

import java.io.File
import java.io.FileNotFoundException

class Vec2 {
    private var data = FloatArray(2)

    init {
        data[0] = 0.0f
        data[1] = 0.0f
    }

    // 2-element string list
    constructor(str: List<String>) {
        for ((i, numStr) in str.withIndex()) {
            val num = numStr.toFloat()
            data[i] = num
        }
    }

    override fun toString(): String {
        val iMax: Int = data.size - 1
        val b = StringBuilder()
        b.append('{')
        var i = 0
        while (true) {
            b.append(data[i])
            if (i == iMax) return b.append('}').toString()
            b.append(", ")
            i++
        }
    }
}

class Vec3 {
    private var data = FloatArray(3)

    init {
        data[0] = 0.0f
        data[1] = 0.0f
        data[2] = 0.0f
    }

    // 3-element string list
    constructor(str: List<String>) {
        for ((i, numStr) in str.withIndex()) {
            val num = numStr.toFloat()
            data[i] = num
        }
    }

    override fun toString(): String {
        val iMax: Int = data.size - 1
        val b = StringBuilder()
        b.append('{')
        var i = 0
        while (true) {
            b.append(data[i])
            if (i == iMax) return b.append('}').toString()
            b.append(", ")
            i++
        }
    }
}

class VertexPropIndexes() {
    private var data = IntArray(3)

    init {
        data[0] = 0
        data[1] = 0
        data[2] = 0
    }

    operator fun set(id: Int, value: Int) {
        data[id] = value
    }

    operator fun get(id: Int): Int {
        return data[id]
    }

    // 3-element string list
    constructor(str: List<String>) : this() {
        for ((i, numStr) in str.withIndex()) {
            val num = numStr.toInt()
            data[i] = num
        }
    }

    override fun toString(): String {
        val iMax: Int = data.size - 1
        val b = StringBuilder()
        b.append('{')
        var i = 0
        while (true) {
            b.append(data[i])
            if (i == iMax) return b.append('}').toString()
            b.append(", ")
            i++
        }
    }
}

class ObjFile(filePath: String) {
    private val lines = mutableListOf<String>()
    private var vertecies = mutableListOf<Vec3>()
    private var norlmals = mutableListOf<Vec3>()
    private var texCoords = mutableListOf<Vec2>()

    private var vertIds = mutableListOf<VertexPropIndexes>()
    private var texcIds = mutableListOf<VertexPropIndexes>()
    private var nrmlIds = mutableListOf<VertexPropIndexes>()

    private var trianglesCount = 0

    init {
        if (filePath.isEmpty()) {
            throw IllegalArgumentException()
        }

        val file = File(filePath)
        if (!file.exists()) {
            throw FileNotFoundException()
        }

        file.inputStream().bufferedReader().forEachLine { lines.add(it) }
        for (line in lines) {

            if (line.startsWith("v ")) {
                val vertexString = line.removePrefix("v ").split(" ")
                val v = Vec3(vertexString)
                vertecies.add(v)

            }

            if (line.startsWith("vn ")) {
                val normalString = line.removePrefix("vn ").split(" ")
                val n = Vec3(normalString)
                norlmals.add(n)
            }

            if (line.startsWith("vt ")) {
                val texCoordString = line.removePrefix("vt ").split(" ")
                val tc = Vec2(texCoordString)
                texCoords.add(tc)
            }

            if (line.startsWith("f ")) {
                val faceString = line.removePrefix("f ").split(" ")
                var veIds = VertexPropIndexes()
                var tcIds = VertexPropIndexes()
                var nmIds = VertexPropIndexes()
                for ((i, face) in faceString.withIndex()) {
                    val vertPropsStr = face.split("/")
                    veIds[i] = vertPropsStr[0].toInt() - 1
                    tcIds[i] = vertPropsStr[1].toInt() - 1
                    nmIds[i] = vertPropsStr[2].toInt() - 1
                }
                vertIds.add(veIds)
                texcIds.add(tcIds)
                nrmlIds.add(nmIds)

                trianglesCount++
            }
        }
    }

    fun out() {
        println("vertecies:")
        for (i in 0..trianglesCount - 1) {
            val vx = vertecies[vertIds[i][0]]
            val vy = vertecies[vertIds[i][1]]
            val vz = vertecies[vertIds[i][2]]
            println("$vx , $vy , $vz ,")
        }

        println("texture coordinates:")
        for (i in 0..trianglesCount - 1) {
            val u = texCoords[texcIds[i][0]]
            val w = texCoords[texcIds[i][1]]
            println("$u , $w ,")
        }

        println("normals:")
        for (i in 0..trianglesCount - 1) {
            val nx = norlmals[nrmlIds[i][0]]
            val ny = norlmals[nrmlIds[i][1]]
            val nz = norlmals[nrmlIds[i][2]]
            println("$nx , $ny , $nz ,")
        }
    }
}