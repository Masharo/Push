interface Push {
    val text: String
    val systemState: SystemState

    //Набор фильтров
    val filters: List<Filter>

    //Применяем фильтры
    fun applyFilters(): String? {
        filters.forEach { filter -> if (filter.applyFilter()) return null }
        return text
    }
}