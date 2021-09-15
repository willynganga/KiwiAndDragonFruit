package ke.co.willynganga.modernhorticulture.model

data class GridItem(
    val username: String,
    val phoneNumber: String,
    val quantity: String,
    val sellingPrice: String,
    val typeOfFruit: String,
    val location: String,
    val imagesUrlList: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GridItem

        if (!imagesUrlList.contentEquals(other.imagesUrlList)) return false

        return true
    }

    override fun hashCode(): Int {
        return imagesUrlList.contentHashCode()
    }

}
