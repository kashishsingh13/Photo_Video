package com.example.photosapplication

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.cardview.widget.CardView

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val linearLayout = LinearLayout(this)
        linearLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.id = R.id.linerlayout

        val horizontalLayout = LinearLayout(this)
        horizontalLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        horizontalLayout.orientation = LinearLayout.HORIZONTAL
        horizontalLayout.weightSum = 100f

        val card1 = CardView(this)
        val card1Params = LinearLayout.LayoutParams(
            0,
            300
        )
        card1Params.weight = 70f
        card1.layoutParams = card1Params
        card1.setCardBackgroundColor(Color.BLACK)

        val card2 = CardView(this)
        val card2Params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            300
        )
        card2Params.weight = 20f
        card2.layoutParams = card2Params
        card2.setCardBackgroundColor(Color.parseColor("#EA0B0B"))

        val card3 = CardView(this)
        val card3Params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            300
        )
        card3Params.weight = 10f
        card3.layoutParams = card3Params
        card3.setCardBackgroundColor(Color.parseColor("#2196F3"))

        horizontalLayout.addView(card1)
        horizontalLayout.addView(card2)
        horizontalLayout.addView(card3)

        val relativeLayout = RelativeLayout(this)
        val relativeLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
//        relativeLayoutParams.height = 100
//        relativeLayoutParams.width = 100
//        (relativeLayoutParams as RelativeLayout.LayoutParams).setMargins(0, 30, 0, 0)
        relativeLayout.layoutParams = relativeLayoutParams

        val editText = EditText(this)
        editText.layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        editText.hint = "kashish"
        editText.id = R.id.name

        val cancelButton = Button(this)
        val cancelButtonParams = RelativeLayout.LayoutParams(
            350,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        cancelButtonParams.addRule(RelativeLayout.BELOW,R.id.name)
        cancelButton.layoutParams= cancelButtonParams


        cancelButton.setText("Cancel")
        cancelButton.id = R.id.cancel

        val okButton = Button(this)
        val okButtonParams = RelativeLayout.LayoutParams(
            350,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        okButtonParams.addRule(RelativeLayout.RIGHT_OF, R.id.cancel)
        okButtonParams.addRule(RelativeLayout.BELOW,R.id.name)

        okButton.layoutParams = okButtonParams
        okButton.setText("Ok")
        okButton.id = R.id.oh

        relativeLayout.addView(editText)
        relativeLayout.addView(cancelButton)
        relativeLayout.addView(okButton)

        val horizontalScrollView = HorizontalScrollView(this)
        val horizontalScrollViewParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
//        horizontalScrollViewParams.marginTop = 30
        (horizontalScrollViewParams as LinearLayout.LayoutParams).setMargins(0, 30, 0, 0)


        horizontalScrollView.layoutParams = horizontalScrollViewParams
        horizontalScrollView.id = R.id.hozi

        val horizontalLinearLayout = LinearLayout(this)
        horizontalLinearLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        horizontalLinearLayout.gravity = Gravity.CENTER
        horizontalLinearLayout.orientation = LinearLayout.HORIZONTAL

        for (i in 0 until 10) {
            val imageView = ImageView(this)
            val layoutParams = LinearLayout.LayoutParams(
                100,
                100
            )
            layoutParams.setMargins(20, 20, 20, 20)
            imageView.layoutParams = layoutParams
            imageView.setBackgroundColor(Color.parseColor("#D5B71F"))

            horizontalLinearLayout.addView(imageView)

        }

        horizontalScrollView.addView(horizontalLinearLayout)

        val scrollView = ScrollView(this)
        val scrollViewParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
//        scrollViewParams.marginTop = 30
        (scrollViewParams as LinearLayout.LayoutParams).setMargins(0, 30, 0, 0)

        scrollView.layoutParams = scrollViewParams
        scrollView.id = R.id.vertical

        val verticalLinearLayout = LinearLayout(this)
        verticalLinearLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        verticalLinearLayout.gravity = Gravity.CENTER
        verticalLinearLayout.orientation = LinearLayout.VERTICAL
        val images = arrayOf(R.drawable.circular, R.drawable.baseline_delete_24, R.drawable.play, R.drawable.ic_launcher_background,R.drawable.circular, R.drawable.baseline_delete_24, R.drawable.play, R.drawable.ic_launcher_background)


        for (i in 0 until 8) {
            val imageView = ImageView(this)
            val layoutParams = LinearLayout.LayoutParams(
                100,
                100
            )
            layoutParams.setMargins(20, 20, 20, 20)
            imageView.layoutParams = layoutParams
//            imageView.setBackgroundColor(Color.parseColor("#D5B71F"))
            imageView.setImageResource(images[i])
            verticalLinearLayout.addView(imageView)
        }

        scrollView.addView(verticalLinearLayout)

        linearLayout.addView(horizontalLayout)
        linearLayout.addView(relativeLayout)
        linearLayout.addView(horizontalScrollView)
        linearLayout.addView(scrollView)

        setContentView(linearLayout)

    }

}