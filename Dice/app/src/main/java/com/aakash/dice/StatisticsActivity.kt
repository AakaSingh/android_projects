package com.aakash.dice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class StatisticsActivity : AppCompatActivity() {

    companion object{
        const val RETURNED_STATISTICS = "returned_stats"
    }
    private lateinit var totalRolls: TextView
    private lateinit var rollsDice1: TextView
    private lateinit var rollsDice2: TextView
    private lateinit var rollsDice3: TextView
    private lateinit var rollsDice4: TextView
    private lateinit var rollsDice5: TextView
    private lateinit var rollsDice6: TextView
    val intentToPassBackwards : Intent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        totalRolls = findViewById(R.id.statistics_totalRolls)
        rollsDice1 = findViewById(R.id.statistics_dice1)
        rollsDice2 = findViewById(R.id.statistics_dice2)
        rollsDice3 = findViewById(R.id.statistics_dice3)
        rollsDice4 = findViewById(R.id.statistics_dice4)
        rollsDice5 = findViewById(R.id.statistics_dice5)
        rollsDice6 = findViewById(R.id.statistics_dice6)
        val resetButton = findViewById<Button>(R.id.statistics_reset)

        val myIntent = intent
        val extra : Bundle? = myIntent.getBundleExtra("bundle")
        intentToPassBackwards.putExtra(RETURNED_STATISTICS,extra)
        setResult(RESULT_OK, intentToPassBackwards)
        val statisticsArray = extra?.getSerializable(MainActivity.EXTRA_STATISTICS) as ArrayList<Int>
        var totalNumberOfRolls = statisticsArray.sum()
        totalRolls.text = resources.getString(R.string.total,totalNumberOfRolls)
            if(totalNumberOfRolls == 0){
                rollsDice1.text = resources.getString(R.string.nullMessage)
            }else{
            rollsDice1.text = statisticsArray?.get(0).toString() + " - " + (statisticsArray?.get(0) * 100/totalNumberOfRolls.toFloat()).toString() + "%"
            rollsDice2.text = statisticsArray?.get(1).toString() + " - " + (statisticsArray?.get(1) * 100/totalNumberOfRolls.toFloat()).toString() + "%"
            rollsDice3.text = statisticsArray?.get(2).toString() + " - " + (statisticsArray?.get(2) * 100/totalNumberOfRolls.toFloat()).toString() + "%"
            rollsDice4.text = statisticsArray?.get(3).toString() + " - " + (statisticsArray?.get(3) * 100/totalNumberOfRolls.toFloat()).toString() + "%"
            rollsDice5.text = statisticsArray?.get(4).toString() + " - " + (statisticsArray?.get(4) * 100/totalNumberOfRolls.toFloat()).toString() + "%"
            rollsDice6.text = statisticsArray?.get(5).toString() + " - " + (statisticsArray?.get(5) * 100/totalNumberOfRolls.toFloat()).toString() + "%"
            }

        resetButton.setOnClickListener(this::resetStatistics)
        }

        private fun resetStatistics(view: View){
            totalRolls.text = resources.getString(R.string.total,0)
            rollsDice1.text = "0"
            rollsDice2.text = "0"
            rollsDice3.text = "0"
            rollsDice4.text = "0"
            rollsDice5.text = "0"
            rollsDice6.text = "0"
            setResult(RESULT_CANCELED, intentToPassBackwards)
        }
    }