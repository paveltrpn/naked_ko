package fibo

import algebra.Matrix2i

fun fibo(count: Int): Long {
    if (count == 1)
        return 0

    if (count == 2 || count == 3)
        return 1

    val pm = Matrix2i(1, 1, 1, 0)
    var res = Matrix2i()
    var next = Matrix2i()

    for (i in 1..<count) {
        res = pm * next
        next = res
    }

    // ret[0] = F(N+1)
    // ret[1] = ret[2] = F(N)
    // ret[3] = F(N-1)
    return res[1]
}

fun main(args: Array<String>) {
    for (i in 1..12) {
        val n = fibo(i)
        println("fibo number $i is $n")
    }
}
