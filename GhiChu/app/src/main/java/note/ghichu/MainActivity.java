package note.ghichu;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import note.ghichu.Model.DBGhiChu;
import note.ghichu.Model.GhiChu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PackageManager packageManager = this.getPackageManager();
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false){
            Toast.makeText(getApplicationContext(), "This device does not have a camera.", Toast.LENGTH_SHORT)
                    .show();
        } else{
            Toast.makeText(getApplicationContext(), "This device does have a camera.", Toast.LENGTH_SHORT)
                    .show();
        }

        try {
            LayGhiChuDuaVaoListView();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabTrangThemMoiGhiChu);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemMoiActivity.class);
                startActivity(intent);
                }
            });
        }

        ListView listView = (ListView) findViewById(R.id.lwDanhSachGhiChu);
        if (listView != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object item = parent.getItemAtPosition(position);
                    GhiChu ghiChuDangChon = (GhiChu) item;

                    Intent intent = new Intent(MainActivity.this, CapNhatActivity.class);
                    Bundle ghiChuBundle = new Bundle();
                    ghiChuBundle.putInt("Id",ghiChuDangChon.getId());
                    ghiChuBundle.putString("TieuDe",ghiChuDangChon.getTieuDe());
                    ghiChuBundle.putString("NoiDung",ghiChuDangChon.getNoiDung());
                    intent.putExtra("GhiChu",ghiChuBundle);
                    startActivity(intent);
                }
            });
        }
    }

    public void LayGhiChuDuaVaoListView() {
        DBGhiChu dbGhiChu = new DBGhiChu(this);
        dbGhiChu.OpenConnection();
        Cursor conTroDanhSachGhiChu = dbGhiChu.TimKiemGhiChu("");
        ListView listView = (ListView) findViewById(R.id.lwDanhSachGhiChu);

        List<GhiChu> danhSachGhiChu = new ArrayList<>();

        if (conTroDanhSachGhiChu != null && conTroDanhSachGhiChu.moveToFirst()) {
            for (conTroDanhSachGhiChu.moveToFirst(); !conTroDanhSachGhiChu.isAfterLast(); conTroDanhSachGhiChu.moveToNext()) {
                int id = Integer.valueOf(conTroDanhSachGhiChu.getString(0));
                String tieuDe = conTroDanhSachGhiChu.getString(1);
                String noiDung = conTroDanhSachGhiChu.getString(2);
                boolean isXoa = Boolean.valueOf(conTroDanhSachGhiChu.getString(3));
                byte[] image = conTroDanhSachGhiChu.getBlob(4);
                GhiChu temp = new GhiChu(id, tieuDe, noiDung, isXoa,image );
                danhSachGhiChu.add(temp);
            }
        }
        if (danhSachGhiChu.size() > 0) {
            GhiChuListViewItem ghiChuListViewItem = new GhiChuListViewItem(danhSachGhiChu, getApplicationContext());
            if (listView != null) {
                listView.setAdapter(ghiChuListViewItem);
            }
        }
        dbGhiChu.CloseConnection();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onResume() {
        super.onResume();
        LayGhiChuDuaVaoListView();
    }
}
