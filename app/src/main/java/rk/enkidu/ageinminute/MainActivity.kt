package rk.enkidu.ageinminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    //textview
    private var view_date : TextView? = null
    private var view_InMinute : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //find id text view
        view_date = findViewById(R.id.view_date)
        view_InMinute = findViewById(R.id.view_InMinute)

        //button
        val btnDate : Button = findViewById(R.id.button_date)

        btnDate.setOnClickListener {
            clickDate()
        }
    }

    private fun clickDate(){
        //mengambil tanggal dalam calender
        val myCalender = Calendar.getInstance();
        val myYear = myCalender.get(Calendar.YEAR)
        val myMonth = myCalender.get(Calendar.MONTH)
        val myDay = myCalender.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                    Toast.makeText(this,"Date selected", Toast.LENGTH_SHORT).show()
                    val date = "$year/${month+1}/$dayOfMonth"
                    view_date?.text = date

                    //selected date
                    val anotherDate = "$dayOfMonth/${month+1}/$year"
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val formatDate = sdf.parse(anotherDate)

                    formatDate?.let {
                        val selectedDateInMinute = formatDate.time / 60000

                        //current date
                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                        currentDate?.let {
                            val currentDateInMinute = currentDate.time / 60000

                            //total
                            val differenceDateInMinutes = currentDateInMinute - selectedDateInMinute

                            if(differenceDateInMinutes < 0){
                                Toast.makeText(this,"You are not born yet", Toast.LENGTH_LONG).show()

                            } else {
                                view_InMinute?.text = differenceDateInMinutes.toString()
                            } }

                    }


        },
        myYear,
        myMonth,
        myDay)

        //so we cant to pick date from the future
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


//        Toast.makeText(this,"pressed", Toast.LENGTH_LONG).show()
    }

}