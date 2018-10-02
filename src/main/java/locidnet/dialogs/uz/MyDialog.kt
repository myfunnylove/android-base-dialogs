package locidnet.dialogs.uz

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.app.DialogFragment
import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView


class MyDialog  : DialogFragment() {

    object Type {

        val IMAGE_CLOSE = 1
        val PROGRESS_CLOSE = 2
        val TEXT_CLOSE = 3
        val TEXT_YES_NO = 4

    }

    object Button {
        val YES = 1
        val NO = 2
        val CLOSE = 3
    }

    interface OnClickListener{
        fun clicked(type : Int)
    }

    lateinit var listener  : OnClickListener

    companion object {
        var mInstance: MyDialog? = null
        val TAG = "MyDialog"
        fun instance(type : Int) : MyDialog {

             mInstance = MyDialog()

            val bundle = Bundle()
            bundle.putInt("type",type)
            mInstance!!.arguments = bundle

            return mInstance!!
        }

        fun instance( type : Int,text : String, no : String, yes : String) : MyDialog {

            mInstance = MyDialog()


            val bundle = Bundle()
            bundle.putInt("type",type)
            bundle.putString("text",text)
            bundle.putString("no",no)
            bundle.putString("yes",yes)
            mInstance!!.arguments = bundle

            return mInstance!!
        }

        fun instance( type : Int, text : String , yes : String) : MyDialog {

            mInstance = MyDialog()


            val bundle = Bundle()
            bundle.putInt("type",type)
            bundle.putString("text",text)
            bundle.putString("yes",yes)
            mInstance!!.arguments = bundle

            return mInstance!!
        }

        fun instance( type : Int, image : Int , yes : String) : MyDialog {
            mInstance = MyDialog()

            val bundle = Bundle()
            bundle.putInt("type",type)
            bundle.putInt("image",image)
            bundle.putString("yes",yes)
            mInstance!!.arguments = bundle

            return mInstance!!
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



       return when(arguments!!.getInt("type")){

            Type.IMAGE_CLOSE -> {
                inflater.inflate(R.layout.dialog_fingerprint,container,false).setImageLayout()
            }
            Type.PROGRESS_CLOSE -> {
                inflater.inflate(R.layout.dialog_loading,container,false).setProgressLayout()
            }
            Type.TEXT_CLOSE -> {
                inflater.inflate(R.layout.dialog_cancel,container,false).setTextLayout()
            }
            Type.TEXT_YES_NO -> {
                inflater.inflate(R.layout.dialog_yes_no,container,false).setTextYesNoLayout()
            }
            else -> {
                inflater.inflate(R.layout.dialog_cancel,container,false).setTextLayout()
            }

        }
    }

    private fun View.setImageLayout() : View{

        val icon = this.findViewById<AppCompatImageView>(R.id.icon)
        icon.setImageDrawable(VectorDrawableCompat.create(resources,arguments!!.getInt("image"),resources.newTheme()))
        val cancel = this.findViewById<TextView>(R.id.cancel)
        cancel.text = arguments!!.getString("yes")
        cancel.setOnClickListener {
            listener.clicked(Button.CLOSE)
        }

        return this
    }

    private fun View.setProgressLayout() : View{



        return this
    }
    private fun View.setTextLayout() : View{


        this.findViewById<TextView>(R.id.text).text = arguments!!.getString("text")
        this.findViewById<TextView>(R.id.yes).run {
            text = arguments!!.getString("yes")
            setOnClickListener {
                listener.clicked(Button.YES)
            }
        }






        return this
    }



    private fun View.setTextYesNoLayout(): View{

        this.findViewById<TextView>(R.id.text).text = arguments!!.getString("text")
        this.findViewById<TextView>(R.id.yes).run {
            text = arguments!!.getString("yes")
            setOnClickListener {
                listener.clicked(Button.YES)
            }
        }
        this.findViewById<TextView>(R.id.no).run {
            text = arguments!!.getString("no")
            setOnClickListener {
                listener.clicked(Button.NO)
            }
        }

        return this
    }

}