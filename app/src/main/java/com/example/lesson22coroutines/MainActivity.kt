package com.example.lesson22coroutines

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val textView:TextView = findViewById(R.id.textView)
		val button:Button = findViewById(R.id.button)
		val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
		button.setOnClickListener {
			viewModel.getData()
		}

		viewModel.uiState.observe(this) {uiState->
			when(uiState){
				is MyViewModel.UIState.Empty -> Unit
				is MyViewModel.UIState.Result -> {
					textView.text = uiState.title
				}
				is MyViewModel.UIState.Processing -> textView.text = "Processing..."
				is MyViewModel.UIState.Error -> {
					textView.text = ""
					Toast.makeText(this, uiState.description, Toast.LENGTH_SHORT).show()
				}
			}
		}
	}
}