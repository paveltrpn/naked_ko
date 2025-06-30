package algebra

open class Matrix<T>(size: Int) {
    // protected var data: Array<T> = arrayOf(4)
}

open class Matrixi(size: Int) {
    protected var data = LongArray(4)
}

class Matrix2i() : Matrixi(4) {
    init {
        data[0] = 1
        data[1] = 0
        data[2] = 0
        data[3] = 1
    }

    constructor(a: Long, b: Long, c: Long, d: Long) : this() {
        data[0] = a
        data[1] = b
        data[2] = c
        data[3] = d
    }

    operator fun get(index: Int): Long {
        return data[index]
    }

    operator fun times(rhs: Matrix2i): Matrix2i {
        val tmp = this.copy()
        tmp.multThis(rhs)
        return tmp
    }

    fun copy(): Matrix2i {
        return Matrix2i(this[0], this[1], this[2], this[3])
    }

    fun compare(rhs: Matrix2i): Boolean {
        return this[0] == rhs[0] && this[1] == rhs[1] && this[2] == rhs[2] && this[3] == rhs[3]
    }

    fun multThis(rhs: Matrix2i) {
        val data0 = data[0]
        val data1 = data[1]
        data[0] = rhs[0] * data[0] + rhs[1] * data[2]
        data[1] = rhs[0] * data[1] + rhs[1] * data[3]
        data[2] = rhs[2] * data0 + rhs[3] * data[2]
        data[3] = rhs[2] * data1 + rhs[3] * data[3]
    }

    fun det(): Long {
        return data[0] * data[3] - data[2] * data[1]
    }

    fun getTranspose(): Matrix2i {
        return Matrix2i(data[0], data[2], data[1], data[3])
    }

    fun show() {
        println(data[0].toString())
        println(data[1].toString())
        println(data[2].toString())
        println(data[3].toString())
    }
}
