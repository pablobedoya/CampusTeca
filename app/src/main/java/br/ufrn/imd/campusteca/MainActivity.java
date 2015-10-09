package br.ufrn.imd.campusteca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.campusteca.adapter.ListViewAdapter;
import br.ufrn.imd.campusteca.model.ListViewItem;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar toolbar;
    private FragmentDrawer drawer;

    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<ListViewItem> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawer.setDrawerListener(this);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
                startActivity(intent);
            }
        });

        populateListView();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }

    private void populateListView() {
        itens = new ArrayList<ListViewItem>();
        ListViewItem item1 = new ListViewItem("Título do Arquivo", R.drawable.archive);
        ListViewItem item2 = new ListViewItem("Título do Arquivo", R.drawable.archive);
        ListViewItem item3 = new ListViewItem("Título do Arquivo", R.drawable.archive);
        ListViewItem item4 = new ListViewItem("Título do Arquivo", R.drawable.archive);

        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        itens.add(item4);

        listViewAdapter = new ListViewAdapter(this, itens);
        listView.setAdapter(listViewAdapter);
    }
}
