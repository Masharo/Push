interface Filter {
    val systemState: SystemState

    fun applyFilter(): Boolean
}