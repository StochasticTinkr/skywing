package com.stochastictinkr.skywing.utils

infix fun Int.containsAllBitsIn(mask: Int) = and(mask) == mask
infix fun Int.containsAllBitsNotIn(mask: Int) = containsAllBitsIn(mask.inv())
infix fun Int.containsOnlyBitsIn(mask: Int) = and(mask) == this
infix fun Int.containsOnlyBitsNotIn(mask: Int) = containsOnlyBitsIn(mask.inv())
infix fun Int.containsNoBitsIn(mask: Int) = and(mask) == 0
infix fun Int.containsAnyBitsIn(mask: Int) = and(mask) != 0
