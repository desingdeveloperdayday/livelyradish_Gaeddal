package com.hyeyeon2371.gaeddal.common

import android.annotation.SuppressLint
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.hyeyeon2371.gaeddal.R

class MenuButtonView : ConstraintLayout{
    constructor(context: Context) : this(context, null)
    constructor(context:Context, attrs:AttributeSet?) : this(context, attrs , 0){
        initView(context, attrs)
    }

    constructor(context: Context, attrs:AttributeSet?, defStyleAttr:Int) : super(context, attrs, defStyleAttr){
        initView(context, attrs)
    }

    @SuppressLint("ServiceCast")
    private fun initView(context: Context, attrs: AttributeSet?) {
        attrs.let {
            val li = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = li.inflate(R.layout.view_menubutton, this, false)

            val typedArray = context.obtainStyledAttributes(it, R.styleable.MenuButtonView, 0, 0)
            val text = typedArray.getText(typedArray.getResourceId(R.styleable.MenuButtonView_text, 0))

            addView(view)
            findViewById<TextView>(R.id.menubutton_text).text =  text.toString()
            typedArray.recycle()
        }

    }

}