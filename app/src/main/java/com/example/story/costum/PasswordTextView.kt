package com.example.story.costum

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.story.R

class PasswordTextView : AppCompatEditText {
    private lateinit var passwordIconDrawable: Drawable

    constructor(context: Context) : super(context) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setup()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        transformTextToPassword()
    }

    private fun setup() {
        passwordIconDrawable =
            ContextCompat.getDrawable(context, R.drawable.icon_lock) as Drawable
        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        compoundDrawablePadding = 16

        setHint(R.string.hintPassword)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setAutofillHints(AUTOFILL_HINT_PASSWORD)
        }

        setDrawable(passwordIconDrawable)
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty() && s.length < 8) {
                    setErrorAndColor(context.getString(R.string.errorPassword), Color.RED)
                } else {
                    setErrorAndColor(null, Color.BLACK)
                }
            }
        })
    }

    private fun setDrawable(
        start: Drawable? = null,
        top: Drawable? = null,
        end: Drawable? = null,
        bottom: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom)
    }

    private fun transformTextToPassword() {
        transformationMethod = PasswordTransformationMethod.getInstance()
    }

    private fun setErrorAndColor(errorMessage: String?, color: Int) {
        error = errorMessage
        setTextColor(color)
    }
}
