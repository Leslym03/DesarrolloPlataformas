package com.example.aplicacionplataformas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerNota extends AppCompatActivity {

    private static final int EDITAR = Menu.FIRST;
    private static final int BORRAR = Menu.FIRST +1;
    private static final int SALIR = Menu.FIRST +2;

    String title, content;
    TextView TITLE, CONTENT;

    AdaptadorBD DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_nota);

        Bundle bundle = this.getIntent().getExtras();
        title = bundle.getString("title");
        content = bundle.getString("content");

        TITLE = (TextView) findViewById(R.id.TextView_Titulo);
        CONTENT = (TextView) findViewById(R.id.TextView_Nota);

        TITLE.setText(title);
        CONTENT.setText(content);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu);
        menu.add(1,EDITAR,0,R.string.menu_editar);
        menu.add(2,BORRAR,0,R.string.menu_eliminar);
        menu.add(3,SALIR,0,R.string.menu_salir);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case EDITAR:
                actividad("edit");
                return true;
            case BORRAR:
                delete();

                return true;
            case SALIR:
                actividad("delete");

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public  void actividad (String f){
        if(f.equals("edit")){
            String type = "edit";
            Intent intent = new Intent(VerNota.this,AgregarNota.class);
            intent.putExtra("type",type);
            intent.putExtra("title",title);
            intent.putExtra("content",content);
            startActivity(intent);

        }
        else{
            if(f.equals("delete")){
                CookieSyncManager.createInstance(this);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();
                Intent intent = new Intent(VerNota.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }



    private  void delete(){
        DB = new AdaptadorBD(this);
        DB.deleteNote(title);
        actividad("delete");

    }


}
