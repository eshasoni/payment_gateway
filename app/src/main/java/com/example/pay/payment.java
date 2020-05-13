package com.example.pay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class payment extends AppCompatActivity implements PaymentResultListener {
    EditText value;
    Button pay;
    int payamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );



        value =(EditText)findViewById( R.id.amount);
        pay =(Button)findViewById( R.id.payment );
        pay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        } );
    }

    private void startPayment() {
        payamount = Integer.parseInt( value.getText().toString() );
        Checkout checkout = new Checkout();
        checkout.setImage( R.mipmap.ic_launcher);
        final Activity activity=this;
        try {
            JSONObject options = new JSONObject(  );
            options.put( "description","order #12345");
            options.put( "currency","INR" );
            options.put("amount",payamount*100);
            checkout.open( activity,options );

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText( payment.this,"your payment is successful",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText( payment.this,"your payment failed",Toast.LENGTH_SHORT).show();

    }
}
