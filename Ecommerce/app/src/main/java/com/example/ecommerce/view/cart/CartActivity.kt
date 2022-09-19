package com.example.ecommerce.view.cart

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.ecommerce.R
import com.example.ecommerce.databinding.ActivityCartBinding
import com.example.ecommerce.local.ProductEntity
import com.example.ecommerce.view.product.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import java.util.*
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


@AndroidEntryPoint
class CartActivity : AppCompatActivity(), OnClickDelete {
    private lateinit var categoryAdapter: CartAdapter
    private val viewModel by viewModels<ProductViewModel>()
    private val newList = mutableListOf<ProductEntity>()
    lateinit var et: EditText
    lateinit var et2: EditText
    lateinit var et3: EditText

    private lateinit var cartActivityBinding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartActivityBinding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(cartActivityBinding.root)
        cartActivityBinding.toolbar.txtCenter.text = getString(R.string.my_cart)
        cartActivityBinding.toolbar.imgBack.visibility = View.VISIBLE
        cartActivityBinding.toolbar.imgBack.setOnClickListener {
            finish()
        }
        setUpRecyclerView()
        buildData()

        cartActivityBinding.btnAddToBag.setOnClickListener {
            showDialog();
        }
    }

    /**
     * Setting up the recyclerview
     */

    private fun setUpRecyclerView() {
        categoryAdapter = CartAdapter(newList, this)
        cartActivityBinding.rvCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    /**
     * Building the data for list from database
     */

    private fun buildData() {
        viewModel.getAllRecord().observe(this, Observer {
            newList.clear()
            newList.addAll(it)
            if (newList.isNullOrEmpty()) {
                cartActivityBinding.emptyCart.visibility = View.VISIBLE
                cartActivityBinding.emptyCart.load("https://book.smartercarrentals.com/images/cart.png")
            } else {
                cartActivityBinding.emptyCart.visibility = View.GONE
            }
            categoryAdapter.notifyDataSetChanged()
        })
        //For total price of cart items
//        viewModel.getTotal().observe(this, Observer {
//            cartActivityBinding.totalPrice.text = it.toString()
//        })
    }

    /**
     * Dialog to show Order and asking for details
     */

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(com.example.ecommerce.R.layout.dialog_layout)
        et = dialog.findViewById(com.example.ecommerce.R.id.et)
        et2 = dialog.findViewById(com.example.ecommerce.R.id.et2)
        et3 = dialog.findViewById(com.example.ecommerce.R.id.et3)
        val btnok: Button = dialog.findViewById(com.example.ecommerce.R.id.btnok) as Button
        btnok.setOnClickListener(View.OnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Your order is successfully placed")
                .setCancelable(false)
                .setPositiveButton("Done", DialogInterface.OnClickListener { dialog, id ->
                    finish()
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Order Details")
            alert.show()
//            sendEmail()
//            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//            StrictMode.setThreadPolicy(policy)
        })
        dialog.show()
    }

    /**
     * Deleting item from cart
     */
    override fun onDeleteListener(product: ProductEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.deleteRecord(product)
        }
    }

    /**
     * Function to send the mail
     */

    fun sendEmail() {
        val userName = "varunwani1997@gmail.com"
        val password = "Varun@123"
        val messageToSend = "Your order will be delivery to following address $et3"
        val prop = Properties()
        prop.put("mail.smtp.auth", "true")
        prop.put("mail.smtp.starttls.enable", "true")
        prop.put("mail.smtp.host", "smtp.gmail.com")
        prop.put("mail.smtp.port", "587")

        val session = Session.getInstance(prop,
            object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(userName, password)
                }
            })
        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(userName))
            message.setRecipients(
                javax.mail.Message.RecipientType.TO,
                InternetAddress.parse(et2.text.toString())
            )
            message.setSubject("Your order is successfully placed")
            message.setText(messageToSend)
            Transport.send(message)
            Toast.makeText(applicationContext, "Email sent", Toast.LENGTH_SHORT).show()

        } catch (e: MessagingException) {
            throw RuntimeException(e)
        }
    }


}