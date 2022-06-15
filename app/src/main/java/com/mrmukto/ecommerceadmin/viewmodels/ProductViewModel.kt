package com.mrmukto.ecommerceadmin.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.mrmukto.ecommerceadmin.models.Product
import com.mrmukto.ecommerceadmin.models.Purchase
import com.mrmukto.ecommerceadmin.repos.ProductRepository
import java.io.ByteArrayOutputStream

class ProductViewModel : ViewModel() {
    val repository = ProductRepository()
    val statusLD = MutableLiveData<String>()
    fun addProduct(product: Product, purchase: Purchase) {
        repository.addProduct(product, purchase) {
            statusLD.value = it
        }
    }

    fun updateOrderSettingsField(field: String, value: Any) = repository.updateOrderSettingsField(field, value)

    fun addPurchase(purchase: Purchase) = repository.addNewPurchase(purchase)
    fun getOrderSettings() = repository.getOrderSettings()
    fun getProducts() = repository.getAllProducts()
    fun getProductByProductId(id: String) = repository.getProductByProductId(id)
    fun getPurchasesByProductId(id: String) = repository.getPurchaseHistoryByProductId(id)
    fun getCategories() = repository.getAllCategories()
    fun uploadImage(bitmap: Bitmap, callback: (String) -> Unit) {
        val photoRef = FirebaseStorage.getInstance().reference
            .child("images/${System.currentTimeMillis()}")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)
        val data: ByteArray = baos.toByteArray()
        val uploadTask = photoRef.putBytes(data)
        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            photoRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result.toString()
                callback(downloadUri)
            } else {
                // Handle failures
                // ...
            }
        }
    }
}