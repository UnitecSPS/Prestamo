package unitec.progra2.android;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class Loan extends ActionBarActivity {
	
	private double balance;
	private TextView tvBalance;
	private EditText etMonto;
	public static final int RESULT_FOR_PAY=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l);
        
        tvBalance = (TextView)this.findViewById(R.id.balance);
        etMonto = (EditText)this.findViewById(R.id.txtMonto);
    }

    public void salvarPrestamo(View v){
    	String monto = etMonto.getText().toString();
    	try{
    		balance = Double.parseDouble(monto);
    		tvBalance.setText("Lps. " + balance);
    	}catch(Exception e){
    		//poner toad de valor malo
    	}
    }
    
    public void pagar(View v){
    	Intent intent = new Intent(getApplicationContext(), PayActivity.class);
    	startActivityForResult(intent, RESULT_FOR_PAY);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(requestCode == RESULT_FOR_PAY && resultCode == RESULT_OK){
    		Bundle intentData = data.getExtras();
    		double pago = Double.parseDouble(intentData.get("BILL").toString());
    		balance -= pago;
    		tvBalance.setText("Lps. " + balance);
    	}
    }
}
