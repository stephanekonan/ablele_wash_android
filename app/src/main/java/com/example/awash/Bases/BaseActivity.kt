package com.example.a5equiz.basesimport android.os.Bundleimport android.widget.ImageButtonimport android.widget.ImageViewimport android.widget.LinearLayoutimport android.widget.TextViewimport android.widget.Toastimport androidx.activity.enableEdgeToEdgeimport androidx.appcompat.app.AppCompatActivityimport androidx.core.view.ViewCompatimport androidx.core.view.WindowInsetsCompatimport androidx.core.view.updatePaddingimport com.example.awash.Config.ConstToastimport com.example.awash.Fragments.DrawerBottomFragmentimport com.example.awash.Rimport java.util.Localeopen class BaseActivity : AppCompatActivity() {    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        "fr".setLocale()        enableEdgeToEdge()    }    private fun String.setLocale() {        val locale = Locale(this)        Locale.setDefault(locale)        val config = resources.configuration        config.setLocale(locale)        resources.updateConfiguration(config, resources.displayMetrics)    }    protected fun showDrawerBottomFragment() {        val drawerBottomFragment = DrawerBottomFragment()        drawerBottomFragment.show(supportFragmentManager, drawerBottomFragment.tag)    }    fun setupBackButton(buttonId: Int) {        val btnToBack = findViewById<ImageButton>(buttonId)        btnToBack.setOnClickListener { finish() }    }    fun setupEdgeToEdge(viewId: Int) {        ViewCompat.setOnApplyWindowInsetsListener(findViewById(viewId)) { v, insets ->            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())            v.updatePadding(                left = systemBars.left,                top = systemBars.top,                right = systemBars.right,                bottom = systemBars.bottom            )            insets        }    }    fun showToast(toastType: Int, message: CharSequence) {        val toastView = layoutInflater.inflate(            R.layout.layout_toast,            findViewById(R.id.layoutToastContainer)        )        val toastLayoutContainer = toastView.findViewById<LinearLayout>(R.id.layoutToastContainer)        val imageIcon = toastView.findViewById<ImageView>(R.id.imageIcon)        val textMessage = toastView.findViewById<TextView>(R.id.textMessage)        when (toastType) {            ConstToast.TOAST_TYPE_SUCCESS -> {                toastLayoutContainer.setBackgroundResource(R.drawable.bg_toast_success)                imageIcon.setImageResource(R.drawable.ic_check_circle)                textMessage.text = message            }            ConstToast.TOAST_TYPE_ERROR -> {                toastLayoutContainer.setBackgroundResource(R.drawable.bg_toast_error)                imageIcon.setImageResource(R.drawable.ic_error)                textMessage.text = message            }            ConstToast.TOAST_TYPE_WARNING -> {                toastLayoutContainer.setBackgroundResource(R.drawable.bg_toast_warning)                imageIcon.setImageResource(R.drawable.ic_warning)                textMessage.text = message            }        }        with(Toast(applicationContext)) {            duration = Toast.LENGTH_SHORT            view = toastView            show()        }    }}