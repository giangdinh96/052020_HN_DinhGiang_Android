package vn.teko.test.presentation.model

data class ProductItem(
    var id: String,
    var name: String,
    var image: String,
    var images: List<String>,
    var priceNumber: Double,
    var finalPrice: String,
    var originPrice: String,
    var discountPercent: String,
    var status: String,
    var attributes: List<ProductAttributeData>
)