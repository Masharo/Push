interface Filter {
    val systemState: SystemState

    fun apply(): Boolean
}