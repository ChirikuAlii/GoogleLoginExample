package info.chirikualii.googleloginexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    companion object {
        val SIGN_IN = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val googleSignIn = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        val signInClient = GoogleSignIn.getClient(this,googleSignIn)


        btn_login.setOnClickListener {
            val intent = signInClient.getSignInIntent()
            startActivityForResult(intent, SIGN_IN)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            if(task.isSuccessful){
                Toast.makeText(this,"Sign in Success",Toast.LENGTH_SHORT).show()
                val tvResult = "${Gson().toJsonTree(task.result)}"

                Log.d(MainActivity::class.java.simpleName,"result : ${tvResult}")

            }else{
                Toast.makeText(this,"Sign in failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
