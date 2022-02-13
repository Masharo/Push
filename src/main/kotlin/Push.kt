interface Push {
    val type: String
    val text: String
    val systemState: SystemState

    //Имена необходимых параметров для конкретного фильтра
    val params: Set<String>

    //Набор фильтров
    val filters: List<Filter>

    //Применяем фильтры
    fun applyFilters(): Boolean {
        filters.forEach { filter -> if (!filter.apply(this)) return false }
        return true
    }
}