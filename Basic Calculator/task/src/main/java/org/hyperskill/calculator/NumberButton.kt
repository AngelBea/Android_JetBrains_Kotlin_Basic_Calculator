package org.hyperskill.calculator


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText

class NumberButton : androidx.appcompat.widget.AppCompatButton, View.OnClickListener{
    constructor(ctx : Context, attr: AttributeSet?) : super(ctx, attr)

    init {
        setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val editText = this.rootView.findViewWithTag<EditText>(context.getString(R.string.tag_edit_text))
        val buttonText = (p0 as NumberButton).text
        val zero = context.getString(R.string.zero)

        when(buttonText){
            "0" -> if (editText.text.toString() != zero) editText.append(buttonText)
            else -> if (editText.text.toString() == zero) editText.setText(buttonText) else editText.append(buttonText)
        }
    }
}

