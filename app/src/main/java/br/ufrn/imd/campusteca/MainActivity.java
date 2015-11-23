package br.ufrn.imd.campusteca;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.campusteca.adapter.ListViewAdapter;
import br.ufrn.imd.campusteca.api.OAuthTokenRequest;
import br.ufrn.imd.campusteca.model.Book;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText paramEditText;
    private Spinner targetSpinner;
    private Button searchBooksButton;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        paramEditText = (EditText) findViewById(R.id.paramEditText);

        List<String> targets = new ArrayList<>();
        targets.add("Título");
        targets.add("Autor");
        targets.add("Assunto");
        targetSpinner = (Spinner) findViewById(R.id.targetSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, targets);
        targetSpinner.setAdapter(adapter);
        targetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        searchBooksButton = (Button) findViewById(R.id.searchBooksButton);
        searchBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String target;
                switch (targetSpinner.getSelectedItemPosition()) {
                    case 0:
                        target = "/titulo";
                        break;

                    case 1:
                        target = "/autor";
                        break;

                    case 2:
                        target = "/assunto";
                        break;

                    default:
                        target = "/titulo";
                        break;
                }

                searchBooks(target, "/" + paramEditText.getText().toString());
            }
        });

        listView = (ListView) findViewById(R.id.booksListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void searchBooks(String target, String param) {
        books = new ArrayList<Book>();

        /*
         * Mock object for book
        Book item1 = new Book("Autor 1", "Título do Livro 1", "1", "2001", 1, 1, R.drawable.book);
        Book item2 = new Book("Autor 2", "Título do Livro 2", "2", "2002", 2, 2, R.drawable.book);
        Book item3 = new Book("Autor 3", "Título do Livro 3", "3", "2003", 3, 3, R.drawable.book);
        Book item4 = new Book("Autor 4", "Título do Livro 4", "4", "2004", 4, 4, R.drawable.book);

        books.add(item1);
        books.add(item2);
        books.add(item3);
        books.add(item4);
        */

        String url = "http://apitestes.info.ufrn.br/biblioteca-services/services/consulta/biblioteca";
        OAuthTokenRequest.getInstance().resourceRequest(this, Request.Method.GET, url + target + param, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Book> books = new ArrayList<Book>();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Book book = new Book();
                        book.setAuthor(jsonObject.getString("autor"));
                        book.setTitle(jsonObject.getString("titulo"));
                        book.setEdition(jsonObject.getString("edicao"));
                        book.setYear(jsonObject.getString("ano"));
                        book.setQuantity(jsonObject.getInt("quantidade"));
                        book.setRegistration(jsonObject.getInt("registroSistema"));

                        books.add(book);
                    }

                    listViewAdapter = new ListViewAdapter(MainActivity.this, books);
                    listView.setAdapter(listViewAdapter);

                    Log.d("quantidade", String.valueOf(books.size()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("SAIDA", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });
    }
}
