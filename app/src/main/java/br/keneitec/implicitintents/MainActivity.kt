package br.keneitec.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ShareCompat
import br.keneitec.implicitintents.databinding.ActivityMainBinding

private lateinit var b: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        intentReciver()

        setupButtons()
    }

    private fun intentReciver() {
        val intent = getIntent()
        val uri = intent.data
        if (uri != null) {
            val uriString = "URI: ${uri.toString()}"
            b.tvText.setText(uriString)
            b.tvInputText.helperText = "Intent Reciver"
        }
    }

    private fun setupButtons() {
        b.btnOpenWebsite.setOnClickListener() { openWebsite() }

        b.btnOpenLocation.setOnClickListener { openLocation() }

        b.btnShareText.setOnClickListener { shareText() }

    }


    private fun openWebsite() {
        val url = b.tvWebsite.text.toString()
        val webPage = Uri.parse(url)

        val i = Intent(Intent.ACTION_VIEW, webPage)

        startActivity(i)
    }

    private fun openLocation() {
        val loc = b.tvLocation.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val i = Intent(Intent.ACTION_VIEW, addressUri)
        startActivity(i)

    }

    private fun shareText() {
        val txt = b.tvText.text.toString()
        val mimeType = "text/plain"

        ShareCompat.IntentBuilder(this)
            .setType(mimeType)
            .setChooserTitle("Share text with: ")
            .setText(txt)//to send text
            //.setEmailTo() to send email
            .startChooser()
    }


}