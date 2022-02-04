package com.aakash.dice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_STATISTICS = "all_stats"
    }
    private lateinit var result: ImageView
    private var images = arrayOf(R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6)
    private var statistics = arrayListOf(0,0,0,0,0,0)

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        run {
            if (result.resultCode == Activity.RESULT_CANCELED) {
                    statistics = arrayListOf(0,0,0,0,0,0)
                } else {
                    val intent = result.data
                    if (intent != null) {
                        if (intent.hasExtra(StatisticsActivity.RETURNED_STATISTICS))
                            run {
                            val extra: Bundle? =
                                intent.getBundleExtra(StatisticsActivity.RETURNED_STATISTICS)
                            statistics = extra?.getSerializable(EXTRA_STATISTICS) as ArrayList<Int>
                        }
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result = findViewById(R.id.main_result)

        val statisticsButton = findViewById<Button>(R.id.main_statistics)
        val rollDice = findViewById<Button>(R.id.main_rollDice)
        rollDice.setOnClickListener(this::onDiceRoll)
        statisticsButton.setOnClickListener(this::toStatsPage)
    }

    private fun onDiceRoll(view: View){
        val diceResultValue = (1..6).random()
        result.setImageResource(images[diceResultValue-1])
        statistics[diceResultValue-1] += 1
    }

    private fun toStatsPage(view: View){
        val extra : Bundle = Bundle()
        extra.putSerializable(EXTRA_STATISTICS,statistics)
        val intent = Intent(this,StatisticsActivity::class.java).apply {
            putExtra("bundle",extra)
        }
        resultLauncher.launch(intent)
    }
}