package sk.stuba.fei.api.mobv.zadanie.data

import sk.stuba.fei.api.mobv.zadanie.data.ISortPubs.SortDirection.ASC

interface ISortPubs {
    enum class SortDirection { ASC, DESC }

    fun sortPubs(direction: SortDirection = ASC)
}