package vn.teko.test.presentation.model

data class ProductItem(
    var id: String,
    var name: String,
    var image: String?,
    var price: String,
    var originPrice: String,
    var discountPercent: String
)