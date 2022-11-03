package sk.stuba.fei.api.mobv.zadanie.data

interface IRemovePub {
    enum class RemoveFrom { WEB, JSON }

    fun removePub(pub: Pub): Boolean
}