package com.example.laboratorio2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNombre = findViewById<EditText>(R.id.editNombre)
        val editEdad = findViewById<EditText>(R.id.editEdad)
        val spinnerDepartamento = findViewById<Spinner>(R.id.spinnerDepartamento)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewDetalles = findViewById<TextView>(R.id.textViewDetalles)

        val nombres = mutableListOf<String>()
        val detalles = mutableMapOf<String, Pair<String, String>>()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres)
        listView.adapter = adapter

        val departamentos = arrayOf(
            "Selecciona un departamento",
            "Ahuachapán",
            "Santa Ana",
            "Sonsonate",
            "Chalatenango",
            "La Libertad",
            "San Salvador",
            "Cuscatlán",
            "La Paz",
            "Cabañas",
            "San Vicente",
            "Usulután",
            "San Miguel",
            "Morazán",
            "La Unión"
        )

        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, departamentos)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDepartamento.adapter = adapterSpinner

        btnAgregar.setOnClickListener {
            val nombre = editNombre.text.toString().trim()
            val edad = editEdad.text.toString().trim()
            val departamento = spinnerDepartamento.selectedItem.toString()

            if (nombre.isEmpty() || edad.isEmpty() || departamento == "Selecciona un departamento") {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            nombres.add(nombre)
            detalles[nombre] = Pair(edad, departamento)
            adapter.notifyDataSetChanged()

            editNombre.setText("")
            editEdad.setText("")
            spinnerDepartamento.setSelection(0)

            Toast.makeText(this, "Registro agregado", Toast.LENGTH_SHORT).show()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val nombreSeleccionado = nombres[position]
            val (edad, departamento) = detalles[nombreSeleccionado]!!
            textViewDetalles.text = "Nombre: $nombreSeleccionado\nEdad: $edad\nDepartamento: $departamento"
        }
    }
}